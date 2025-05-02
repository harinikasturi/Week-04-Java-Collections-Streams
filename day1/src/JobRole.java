
import java.util.ArrayList;
import java.util.List;
public abstract class JobRole {
    private String title;
    private String department;

    public JobRole(String title, String department) {
        this.title = title;
        this.department = department;
    }

    public String getTitle() { return title; }
    public String getDepartment() { return department; }

    public abstract String[] getRequiredSkills();
}

class SoftwareEngineer extends JobRole {
    public SoftwareEngineer() {
        super("Software Engineer", "Engineering");
    }

    @Override
    public String[] getRequiredSkills() {
        return new String[]{"Java", "Python", "Data Structures", "Algorithms"};
    }
}

class DataScientist extends JobRole {
    public DataScientist() {
        super("Data Scientist", "Data Science");
    }

    @Override
    public String[] getRequiredSkills() {
        return new String[]{"Python", "Machine Learning", "Statistics", "Data Visualization"};
    }
}

class Resume<T extends JobRole> {
    private String candidateName;
    private T targetJob;
    private List<String> skills = new ArrayList<>();

    public Resume(String candidateName, T targetJob) {
        this.candidateName = candidateName;
        this.targetJob = targetJob;
    }

    public void addSkill(String skill) {
        skills.add(skill);
    }

    public boolean isQualified() {
        for (String requiredSkill : targetJob.getRequiredSkills()) {
            if (!skills.contains(requiredSkill)) {
                return false;
            }
        }
        return true;
    }

    public void displayResume() {
        System.out.println("Candidate: " + candidateName);
        System.out.println("Target Position: " + targetJob.getTitle() + " (" + targetJob.getDepartment() + ")");
        System.out.println("Skills: " + String.join(", ", skills));
        System.out.println("Qualified: " + (isQualified() ? "Yes" : "No"));
    }

    public static void screenResumes(List<? extends JobRole> jobRoles, List<Resume<? extends JobRole>> resumes) {
        for (Resume<? extends JobRole> resume : resumes) {
            System.out.println("--- Screening Resume for " + resume.targetJob.getTitle() + " ---");
            resume.displayResume();
            System.out.println();
        }
    }
}

class Main4 {
    public static void main(String[] args) {
        Resume<SoftwareEngineer> devResume = new Resume<>("John Doe", new SoftwareEngineer());
        devResume.addSkill("Java");
        devResume.addSkill("Python");
        devResume.addSkill("Data Structures");
        devResume.addSkill("Algorithms");

        Resume<DataScientist> dsResume = new Resume<>("Jane Smith", new DataScientist());
        dsResume.addSkill("Python");
        dsResume.addSkill("Statistics");
        dsResume.addSkill("Data Visualization");

        List<JobRole> jobRoles = new ArrayList<>();
        jobRoles.add(new SoftwareEngineer());
        jobRoles.add(new DataScientist());

        List<Resume<? extends JobRole>> resumes = new ArrayList<>();
        resumes.add(devResume);
        resumes.add(dsResume);

        Resume.screenResumes(jobRoles, resumes);
    }
}