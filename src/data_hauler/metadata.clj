(ns data-hauler.metadata
  (:gen-class)
  (:require [next.jdbc :as jdbc]
            [next.jdbc.connection :as connection]
            [next.jdbc.result-set :as rs]
            [next.jdbc.datafy :as df])
  (:import [com.zaxxer.hikari HikariDataSource]))

(defn get-resultset-meta [connection-spec query]
  (with-open [^HikariDataSource pooled-ds (connection/->pool HikariDataSource connection-spec)]
    (reduce (fn [_ row] (reduced (rs/metadata row)))
            nil
            (jdbc/plan
              pooled-ds [query]))))

(defn to-name->meta [entry]
  (let [name (:name entry)
        cut-entry (dissoc entry :name)] (assoc {} (keyword name) cut-entry)))

(defn transform-to-name->meta [md] (into {} (map to-name->meta md)))

(defn get-meta [db-spec query]
  (with-open [^HikariDataSource pooled-ds (connection/->pool HikariDataSource db-spec)]
    (transform-to-name->meta (reduce (fn [_ row] (reduced (rs/metadata row)))
                                     nil
                                     (jdbc/plan
                                       pooled-ds [query])))))