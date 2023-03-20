(ns data-retriever.setup
  (:gen-class)
  (:require [data-retriever.request :refer [get-items]]
            [data-retriever.handleDB :refer [insert-data]]))

(defn item-to-map [item] 
  (assoc (second item) "_id" (first item)))

(defn setup-items []
  (println "\n")
  (println "Setting items up ...")
  (println "\n")
  (let [items (get-items)]
    (doseq [item (get-in items ["data"])]
           (insert-data (item-to-map item) "Items"))
    ))

(defn setup! []
  (println "\n\n")
  (println "Setting up data!")
  (println "\n\n")
  ;; (setup-items)
  )