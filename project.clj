(defproject cweb3 "0.1.0-SNAPSHOT"
            :description "FIXME: write description"
            :url "http://example.com/FIXME"
            :license {:name "Eclipse Public License"
                      :url  "http://www.eclipse.org/legal/epl-v10.html"}
            :dependencies [[org.clojure/clojure "1.6.0"]
                           [ring "1.3.2"]
                           [overtone "0.9.1"]]
            :plugins [[lein-ring "0.8.13"]
                      [lein-cljsbuild "1.0.3"]]
            :ring {:handler cweb3.core/full-handler
                   :init    cweb3.core/on-init
                   :port    4001
                   :destroy cweb3.core/on-destroy})
