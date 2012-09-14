<?php
 
class DB_Connect {
 
    // Konstruktor
    function __construct() {
 
    }
 
    // Destructor
    function __destruct() {
        // $this->close();
    }
 
    // Verbinden mit der database
    public function connect() {
        require_once 'config.php';
        // Verbinden mit mysql
        $con = mysql_connect(DB_HOST, DB_USER, DB_PASSWORD);
        // Auswählen database
        mysql_select_db(DB_DATABASE);
 
        // Rückgabe des database handler
        return $con;
    }
 
    // schliessen der Verbindung zur database
    public function close() {
        mysql_close();
    }
 
}
 
?>