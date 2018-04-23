package pm.com.processmanagement;

public class ProcessInfo {

    private boolean selected;

    private int pid;

    private String name;

    public ProcessInfo(int pid, String name){
        this.pid = pid;
        this.name = name;
        this.selected = false;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
