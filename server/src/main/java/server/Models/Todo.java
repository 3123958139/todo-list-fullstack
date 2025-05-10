package server.Models;

/**
 * Todo实体类，表示待办事项
 */
public class Todo {
    /** 待办事项的唯一标识 */
    private int id;
    
    /** 待办事项的标题 */
    private String title;
    
    /** 待办事项的完成状态 */
    private boolean completed;

    /**
     * 获取待办事项ID
     * @return 待办事项ID
     */
    public int getId() {
        return id;
    }

    /**
     * 获取待办事项标题
     * @return 待办事项标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 获取待办事项完成状态
     * @return true表示已完成，false表示未完成
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * 设置待办事项ID
     * @param id 待办事项ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 设置待办事项标题
     * @param title 待办事项标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 设置待办事项完成状态
     * @param completed true表示已完成，false表示未完成
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    /**
     * 无参构造函数
     */
    public Todo() {
    }

    /**
     * 带参构造函数
     * @param id 待办事项ID
     * @param title 待办事项标题
     * @param completed 待办事项完成状态
     */
    public Todo(int id, String title, boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }
}
