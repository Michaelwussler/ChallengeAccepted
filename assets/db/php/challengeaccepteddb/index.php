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

        $name = $_POST['name'];
        $email = $_POST['email'];
        $telefonnummer = $_POST['telefonnummer'];
    
        $anmelden = $db->anmelden($name, $email, $telefonnummer);
            if ($anmelden != false) {
              $response["success"] = 1;
              $response["error_msg"] = "Benutzer geladen";
              echo json_encode($response);
            } else {
              $response["error"] = 1;
              $response["error_msg"] = "Fehler beim Anmelden des Benutzers";
            echo json_encode($response);
            }
    }elseif ($tag == 'isuser') {
        //tag der aufrufenden userfunction ist "isuser"
        //Übergabe der Parameter aus dem json Objekt

        $telefonnummer = $_POST['telefonnummer'];
    
        $isuser = $db->isUser($telefonnummer);
            if ($isuser != false) {
              $response["success"] = 1;
              $response["error_msg"] = "Ueberpruefung erfolgreich"; 
              echo json_encode($response);
            } else {
              $response["error"] = 1;
              $response["error_msg"] = "Fehler beim ueberpruefen des Nutzers";
            echo json_encode($response);
            }
   }elseif ($tag == 'removeuser') {
        //tag der aufrufenden userfunction ist "removeuser"
        //Übergabe der Parameter aus dem json Objekt

        $email_telefonnummer = $_POST['email_telefonnummer'];
    
        $isuser = $db->removeUser($email_telefonnummer);
            if ($isuser != false) {
              $response["success"] = 1;
              $response["error_msg"] = "loeschen des Profils erfolgreich"; 
              echo json_encode($response);
            } else {
              $response["error"] = 1;
              $response["error_msg"] = "Fehler beim loeschen des Profils";
            echo json_encode($response);
            }

   }elseif ($tag == 'profilsync') {

        $anzahlProfile = intval($_POST['anzahlProfile']);

          for($count =0; $count < $anzahlProfile; $count++) {
          $argtelefonummer = $_POST['profil' . (string)$count]; 
          $profilsync = $db->profilSync($argtelefonnummer);
            
            if ($profilsync != false) {
              $response["success"] = 1;
              $response["error_msg"] = "profile erfolgreich uebermittelt.";
              echo json_encode($response);
            } else {
              $response["error"] = 1;
              $response["error_msg"] = "Fehler beim syncen der Profile";
              echo json_encode($response);
            }
          }
        
   }else {
        echo "Ungueltige Anfrage";
    }

 }else {
    echo "Zugriff verweigert";
}
?>