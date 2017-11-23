(ns clojure-website.web
  (:require [compojure.core :refer [defroutes GET PUT POST DELETE ANY]]
            [compojure.handler :refer [site]]
            [compojure.route :as route]
            [clojure.java.io :as io]
            [ring.adapter.jetty :as jetty]
            [environ.core :refer [env]]

            ;; [clojure-website.templ :refer [compile-teplate]]
            ))

(load "templ")

(defn splash [dict]
  ;; FIXME
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (clojure-website.templ/compile-template
          dict
          (slurp (io/resource "public/index.html")))})

(defroutes app
  (GET "/" [& args]
       (println (str "Access args:" args))
       (splash args))
  (route/resources "/")
  ;;ANY "*" []
  (route/not-found (slurp (io/resource "404.html")))
  ;;)
)

(defn -main [& [port]]
  (let [port (Integer. (or port (env :port) 5000))]
    (jetty/run-jetty (site #'app) {:port port :join? false})))

;; For interactive development:
;; (.stop server)
;; (def server (-main))

