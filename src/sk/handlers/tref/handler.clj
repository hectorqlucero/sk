(ns sk.handlers.tref.handler
  (:require [sk.models.crud :refer [db Query]]
            [sk.models.util :refer [parse-int
                                    get-image
                                    current_year]]))

;; Start get-users
(def get-users-sql
  "SELECT
  id AS value,
  CONCAT(firstname,' ',lastname) AS text
  FROM users
  ORDER BY
  firstname,lastname")

(defn get-users []
  "Gets all users from database :ex: (get-users)"
  (Query db [get-users-sql]))
;; End get-users

;; Start get-users-email
(def get-users-email-sql
  "SELECT
  email
  FROM users
  WHERE email = ?")

(defn get-users-email [email]
  "Returns user email or nil"
  (first (Query db [get-users-email-sql email])))
;; End get-users-email

(defn months []
  "Returns months name ex: (months)"
  (list
    {:value 1 :text "January"}
    {:value 2 :text "February"}
    {:value 3 :text "March"}
    {:value 4 :text "April"}
    {:value 5 :text "May"}
    {:value 6 :text "June"}
    {:value 7 :text "July"}
    {:value 8 :text "August"}
    {:value 9 :text "September"}
    {:value 10 :text "October"}
    {:value 11 :text "November"}
    {:value 12 :text "Dicember"}))

(defn imagen [table field idname value & extra-folder]
  (get-image table field idname value (first extra-folder)))

(defn get-item 
  "Generic get field value from table"
  [table field idname idvalue]
  (let [sql (str "SELECT " field " FROM " table " WHERE " idname "='" idvalue "'")
        row (first (Query db sql))]
    ((keyword field) row)))
