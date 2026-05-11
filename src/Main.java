public class Main{
    static void main(String[] args) {
        Experiment experiment = new Experiment();
        experiment.runMultipleTests(); // Runs tests on Small, Medium, Large
        experiment.printResults();
    }
}