(ns cweb3.core)


(defn exception-middleware-fn [handler request]
  (try (handler request)
    (catch Throwable e
      {:status :500 :body (apply str (interpose "\n" (.gerStrachTrace e)))})))

(defn wrap-exception-middleware [handler]
  (fn [request]
    (exception-middleware-fn handler request)))

(defn simple-log-middleware [handler]
  (fn [{:keys [uri] :as request}]
    (println "Request path: " uri)
    (handler request)))

(defn exception-handler [request]
  {:body   (java.io.File. "text.txt")
   :status 500})

(defn not-found-middleware [handler]
  (fn [request]
    (or (handler request)
        {:status 404 :body (str "404 Not Found (with middleware!):" (:uri request))})))


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
  (-> route-handler
      not-found-middleware
      wrap-exception-middleware
      simple-log-middleware))

