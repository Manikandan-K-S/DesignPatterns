
public class FacadePatternDemo {
    public static void main(String[] args) {
        VideoStreamingFacade facade = new VideoStreamingFacade();
        facade.playMovie("movie.mp4");
    }
}

// Subsystem 1
class VideoFile {
    private String filename;
    public VideoFile(String filename) {
        this.filename = filename;
    }
    public String getFilename() { return filename; }
}

// Subsystem 2
class VideoDecoder {
    public void decode(VideoFile file) {
        System.out.println("Decoding video file: " + file.getFilename());
    }
}

// Subsystem 3
class SubtitleLoader {
    public void load(String filename) {
        System.out.println("Loading subtitles for: " + filename);
    }
}

// Subsystem 4
class StreamingServer {
    public void connect() {
        System.out.println("Connecting to streaming server...");
    }
    public void stream(VideoFile file) {
        System.out.println("Streaming: " + file.getFilename());
    }
}

// Façade
class VideoStreamingFacade {
    private VideoDecoder decoder = new VideoDecoder();
    private SubtitleLoader subtitleLoader = new SubtitleLoader();
    private StreamingServer server = new StreamingServer();

    public void playMovie(String filename) {
        System.out.println("Preparing to play: " + filename);
        VideoFile file = new VideoFile(filename);
        decoder.decode(file);
        subtitleLoader.load(filename);
        server.connect();
        server.stream(file);
        System.out.println("Movie started successfully!");
    }
}
 
    

