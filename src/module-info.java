module course.project.tbd {
    requires javafx.fxml;
    requires javafx.controls;
    requires java.desktop;
    requires junit;

    opens Phase_1.GUI.src to javafx.fxml;
    exports Phase_1.GUI.src;
    exports Phase_1Test;
    exports Phase_1.UseCaseClass;
    exports Phase_1.Entity;
    exports Phase_1.Alarm;
    exports Phase_1.GUI;
    opens Phase_1.GUI to javafx.fxml;
}