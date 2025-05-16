import org.opencv.core.*;
import org.opencv.face.LBPHFaceRecognizer;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.imgproc.Imgproc;

public class FaceRecognition {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) {
        CascadeClassifier faceDetector = new CascadeClassifier("haarcascade_frontalface_alt.xml");
        LBPHFaceRecognizer recognizer = LBPHFaceRecognizer.create();
        recognizer.read("trainer.yml");

        VideoCapture camera = new VideoCapture(0);
        Mat frame = new Mat();
        int[] prediction = new int[1];
        double[] confidence = new double[1];

        while (true) {
            camera.read(frame);
            Mat gray = new Mat();
            Imgproc.cvtColor(frame, gray, Imgproc.COLOR_BGR2GRAY);
            Rect[] faces = faceDetector.detectMultiScale(gray);

            for (Rect rect : faces) {
                Mat face = new Mat(gray, rect);
                recognizer.predict(face, prediction, confidence);
                System.out.println("Predicted ID: " + prediction[0] + ", Confidence: " + confidence[0]);
                Imgproc.rectangle(frame, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(255, 0, 0));
            }

            if (!frame.empty()) {
                Imgcodecs.imwrite("output.jpg", frame);
                break;
            }
        }

        camera.release();
    }
}