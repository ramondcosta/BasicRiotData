(ns data-retriever.setup
  (:gen-class)
  (:require [data-retriever.request :refer [get-items]]
            [data-retriever.handleDB :refer [insert-data]]))

(defn setup-items []
  (println "\n")
  (println "Setting items up ...")
  (println "\n")
  (insert-data (get-items) "Items"))

(defn setup! []
  (println "\n\n")
  (println "Setting up data!")
  (println "\n\n")
  (setup-items))