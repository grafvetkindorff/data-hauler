(ns data-hauler.core
  (:gen-class)
  (:require [next.jdbc :as jdbc]
            [next.jdbc.connection :as connection]
            [next.jdbc.datafy :as df]
            [cambium.core  :as log])
  (:import [com.zaxxer.hikari HikariDataSource]))

;; Specs definitions
(def db-spec
  {:jdbcUrl  "jdbc:sqlserver://10.8.0.18:1433"
   :username "sa"
   :password ""})

;(def ds (jdbc/get-datasource db-spec))
(def query "SELECT TOP 100 * FROM DIFF_data.dbo.TableForTest")

(def dimensions-measures-spec
  {:dimensions {:CheckDate {:class "java.sql.Timestamp", :type "datetime"}
                :ChequeID  {:class "java.lang.String", :type "varchar"}
                :magname   {:class "java.lang.String", :type "varchar"}}
   :measures   {:NDS     {:class "java.math.BigDecimal", :type "money"}
                :sebzak {:class "java.lang.Double", :type "float"}}})


;; Handlers
(defn handle-dimension [pair]
  (log/info (str "Dimension: " pair))
  (str "Dimension: " pair))

(defn handle-measure [pair]
  (str "Measure: " pair))

(defn handle [dimensions-measures-spec row]
  (filter #(not= nil %)
          (for [[column-name value] row]
            (cond
              (not= nil (get-in dimensions-measures-spec [:dimensions column-name]))
                (handle-dimension (assoc {} column-name value))
              (not= nil (get-in dimensions-measures-spec [:measures column-name]))
                (handle-measure (assoc {} column-name value))
              :else nil))))

(def handle-fn (partial handle dimensions-measures-spec))

(defn run-import [db-spec query dimensions-measures-spec]
  (with-open [^HikariDataSource pooled-ds (connection/->pool HikariDataSource db-spec)]
    (map handle-fn (jdbc/execute! pooled-ds [query]))))

(defn -main [& args]
  (run-import db-spec query dimensions-measures-spec))
