module com.example.venture_engine {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;


    opens com.example.venture_engine to javafx.fxml;
    exports com.example.venture_engine;
    
}