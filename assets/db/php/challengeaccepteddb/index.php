<?php
/*
 * Alle Requestes werden hier gehandelt
 * Nimmt die GET und POST requests auf
 *
 * Jeder Request enthält einen TAG zu identifikation
 * Antwort wird als JSON data geschickt
 * gecheckt wird für POST Request
 */

if (isset($_POST['tag']) && $_POST['tag'] != '') {
    // get tag
    $tag = $_POST['tag'];
 
    // db handler include
    require_once 'include/DB_Functions_challenge.php';
    $db = new DB_Functions();
 
    //  Array zur response
    $response = array("tag" => $tag, "success" => 0, "error" => 0);
 
    // der tag wird überprüft
    if ($tag == 'anmelden') {
    	//tag der aufrufenden userfunction ist "anmelden"
        //Übergabe der Parameter aus dem json Objekt

        $vorname = $_POST['vorname'];
        $nachname = $_POST['nachname'];
        $email = $_POST['email'];
        $telefonnummer = $_POST['telefonnummer'];
    
        $anmelden = $db->anmelden($vorname, $nachname, $email, $telefonnummer);
            if ($anmelden != false) {
              $response["success"] = 1;
              $response["error_msg"] = "Benutzer geladen";
              echo json_encode($response);
            } else {
              $response["error"] = 1;
              $response["error_msg"] = "Fehler beim Anmelden des Benutzers";
            echo json_encode($response);
            }
    }else {
        echo "Ungültige Anfrage";
    }
} else {
    echo "Zugriff verweigert";
}
?>