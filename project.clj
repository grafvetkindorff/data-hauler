(defproject data-hauler "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}

  :dependencies [[org.clojure/clojure "1.10.1"]

                 [cambium/cambium.core         "1.0.0"]
                 [cambium/cambium.codec-simple "1.0.0"]
                 [cambium/cambium.logback.core "0.4.4"]

                 [com.stuartsierra/component "1.0.0"]

                 ;; https://mvnrepository.com/artifact/seancorfield/next.jdbc
                 [seancorfield/next.jdbc "1.1.569"]
                 ;; https://mvnrepository.com/artifact/com.microsoft.sqlserver/mssql-jdbc
                 [com.microsoft.sqlserver/mssql-jdbc "8.4.0.jre8"]
                 ;; https://mvnrepository.com/artifact/org.postgresql/postgresql
                 [org.postgresql/postgresql "42.2.14"]

                 ;; https://mvnrepository.com/artifact/com.zaxxer/HikariCP
                 [com.zaxxer/HikariCP "3.4.5"]
                 ;; https://mvnrepository.com/artifact/org.apache.arrow/arrow-memory-unsafe

                 [org.apache.arrow/arrow-memory-unsafe "2.0.0"]
                 ;; https://mvnrepository.com/artifact/org.apache.arrow/arrow-memory-core
                 [org.apache.arrow/arrow-memory-core "2.0.0"]
                 ;; https://mvnrepository.com/artifact/org.apache.arrow/arrow-vector
                 [org.apache.arrow/arrow-vector "2.0.0"]]


  :jvm-opts []
  :repl-options {:init-ns data-hauler.core})
