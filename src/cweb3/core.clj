(ns cweb3.core)

(defn example-handler [request]
  {:body "Hello Clojure - reload!"})

(defn on-init []
  (println "Initializing sample webapp!"))

(defn on-destroy []
  (println "Destroing sample webapp!"))