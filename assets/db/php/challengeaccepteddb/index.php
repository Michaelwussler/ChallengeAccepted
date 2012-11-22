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


    }elseif ($tag == 'areusers') {

          $anzahlProfile = intval($_POST['anzahlProfile']);
          $array = array();
        //  $array = array('name' => 'challenges');

            for($count =0; $count < $anzahlProfile; $count++) {
                $response["success"] = 0;
                $response["success"] = 0;
            $argtelefonnummer = $_POST['arg'.(string)$count]; 

            $array = $db->areUsers($argtelefonnummer, $count, $array);

           //   if ($areusers != false) {
           //     $response["success"] = 1;

            //    echo json_encode($response);
            //  } else {
            //    $response["error"] = 1;
            //    echo json_encode($response);
            //  }
          }
          echo json_encode($array);

   }elseif ($tag == 'createtable') {

          $telefonnummer = $_POST['telefonnummer'];
          $create = $db->createTable($telefonnummer);
          
            if ($create != false) {
              $response["success"] = 1;
              $response["error_msg"] = "table erfolgreich erstellt";
              echo json_encode($response);
            } else {
              $response["error"] = 1;
              $response["error_msg"] = "Fehler beim erstellen des table";
              echo json_encode($response);
            }
          
          
    }elseif ($tag == 'droptable') {

        $telefonnummer = $_POST['telefonnummer'];
        $drop = $db->dropTable($telefonnummer);
        
          if ($drop != false) {
            $response["success"] = 1;
            $response["error_msg"] = "table erfolgreich geloescht";
            echo json_encode($response);
          } else {
            $response["error"] = 1;
            $response["error_msg"] = "Fehler beim loeschen des table";
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
        
   }elseif ($tag == 'challengenew') {

        $title = $_POST['title'];
        $description = $_POST['description'];
        $receiver = $_POST['receiver'];
        $sender = $_POST['sender'];
        $channel = $_POST['channel'];
        $status = $_POST['status'];
        $time = $_POST['time'];

    
        $addchallenge = $db->addChallenge($title, $description, $receiver, $sender, $channel, $status, $time);
            if ($addchallenge != false) {
              $response["success"] = 1;
              $response["error_msg"] = "Challenge erstellt";
              echo json_encode($response);
            } else {
              $response["error"] = 1;
              $response["error_msg"] = "Fehler beim erstellen der Challenge";
            echo json_encode($response);
            }
        
   }elseif ($tag == 'addproof') {
        $user = $_POST['user'];
        $id = $_POST['id'];
        $proof = $_POST['proof'];

    
        $addproof = $db->addProof($user, $id, $proof);
            if ($addproof != false) {
              $response["success"] = 1;
              $response["error_msg"] = "Beweis erbracht";
              echo json_encode($response);
            } else {
              $response["error"] = 1;
              $response["error_msg"] = "Beweis konnte nicht erbracht werden";
            echo json_encode($response);
            }
        
   }elseif ($tag == 'getupdatedchallenges') {
        $user = $_POST['user'];
    
        $getupdated = $db->getUpdatedChallenges($user);
            if ($getupdated != false) {
              $response["success"] = 1;
              $response["error_msg"] = "geaenderte challenges geladen";
              echo json_encode($response);
            } else {
              $response["error"] = 1;
              $response["error_msg"] = "geaenderte challenges konnten nicht geladen werden";
            echo json_encode($response);
            }    
   }

   else {
        echo "Ungueltige Anfrage";
    }

 }else {
    echo "Zugriff verweigert";
}
?>