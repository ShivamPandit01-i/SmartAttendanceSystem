import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.imgproc.Imgproc;

public class FaceDatasetCreator {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) {
        int sampleCount = 0;
        int maxSamples = 50;
        int userId = 1;

        CascadeClassifier faceDetector = new CascadeClassifier("haarcascade_frontalface_alt.xml");
        VideoCapture camera = new VideoCapture(0);
        Mat frame = new Mat();

        while (sampleCount < maxSamples) {
            camera.read(frame);
            Mat gray = new Mat();
            Imgproc.cvtColor(frame, gray, Imgproc.COLOR_BGR2GRAY);
            Rect[] faces = faceDetector.detectMultiScale(gray);

            for (Rect rect : faces) {
                Mat face = new Mat(gray, rect);
                Imgcodecs.imwrite("dataset/user." + userId + "." + sampleCount + ".jpg", face);
                sampleCount++;
            }

            if (sampleCount >= maxSamples) break;
        }

        camera.release();
    }
}