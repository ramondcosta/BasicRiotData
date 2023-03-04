(ns data-retriever.core
  (:gen-class)
  (:require [data-retriever.request :refer [find-it print-user]]))


(def obj (find-it))
(println obj)
(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "\n\n")
  (println "user")
  (prn (print-user)))


(println "Isso daqui é um teste! VINICIUS")
