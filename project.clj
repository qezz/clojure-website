(defproject clojure-getting-started "1.0.0-SNAPSHOT"
  :description "Dummy website with clojure backend"
  :url "http://fix-heroku-app-link/"
  :license {:name "No License"
            :url "https://choosealicense.com/no-license/"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.4.0"]
                 [ring/ring-jetty-adapter "1.4.0"]
                 [environ "1.0.0"]]
  :min-lein-version "2.0.0"
  :plugins [[environ/environ.lein "0.3.1"]]
  :hooks [environ.leiningen.hooks]
  :uberjar-name "clojure-website-standalone.jar"
  :profiles {:production {:env {:production true}}})
