(ns cweb3.core)

(defn simple-log-middleware [handler]
  (fn [{:keys [uri] :as request}]
    (println "Request path: " uri)
    (handler request)))

(defn example-handler [request]
  {:body   (java.io.File. "text.txt")
   :status 500})

(defn on-init []
  (println "Initializing sample webapp!"))

(defn on-destroy []
  (println "Destroing sample webapp!"))

(defn test1-handler [request]
  {:body "test1"})

(defn test2-handler [request]
  {:status 301 :headers {"Location" "http://github.com/ring-clojure"}})

(defn route-handler [request]
  (condp = (:uri request)
    "/test1" (test1-handler request)
    "/test2" (test2-handler request)
    nil))

(defn wrapping-handler [request]
  (if-let [resp (route-handler request)]
    resp
    {:status 404 :body
             (str "Not Found: "
                  (:uri request))}))

(def full-handler
  (simple-log-middleware wrapping-handler))