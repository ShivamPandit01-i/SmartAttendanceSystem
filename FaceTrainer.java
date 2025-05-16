import org.opencv.core.*;
import org.opencv.face.LBPHFaceRecognizer;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FaceTrainer {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) {
        File datasetDir = new File("dataset");
        File[] files = datasetDir.listFiles();
        List<Mat> images = new ArrayList<>();
        List<Integer> labels = new ArrayList<>();

        for (File file : files) {
            String[] parts = file.getName().split("\.");
            int label = Integer.parseInt(parts[1]);
            Mat img = Imgcodecs.imread(file.getAbsolutePath(), Imgcodecs.IMREAD_GRAYSCALE);
            Imgproc.resize(img, img, new Size(200, 200));
            images.add(img);
            labels.add(label);
        }

        LBPHFaceRecognizer recognizer = LBPHFaceRecognizer.create();
        recognizer.train(images, new MatOfInt(labels.stream().mapToInt(i -> i).toArray()));
        recognizer.save("trainer.yml");
    }
}