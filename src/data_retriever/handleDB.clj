(ns data-retriever.handleDB
  (:gen-class)
  (:require [monger.core :as mg]
            [monger.collection :as mc])
  (:import [com.mongodb MongoOptions ServerAddress]))

(defn find-it []
  (let [conn (mg/connect)
        db (mg/get-db conn "myNewDB")
        coll "myNewCollection1"]
    (mc/find-maps db coll {:x 3})))

(defn insert-user [user]
  (let [conn (mg/connect)
        db (mg/get-db conn "myNewDB")
        coll "myNewCollection1"
        ]
    (prn user)
    (mc/insert db coll user)))

(defn insert-data [data collection-name]
  (let [conn (mg/connect)
        db (mg/get-db conn "myNewDB")] 
    (mc/insert db collection-name data)))