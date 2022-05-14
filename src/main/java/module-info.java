module me.joey.cse148javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens me.joey.cse148javafx to javafx.fxml;
    exports me.joey.cse148javafx;
    exports me.joey.cse148javafx.finalProject;
    exports me.joey.cse148javafx.projects.project3;
    opens me.joey.cse148javafx.projects.project3 to javafx.fxml;
}