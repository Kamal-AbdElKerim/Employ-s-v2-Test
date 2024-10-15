package model.Auth;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "employee_changes")
public class EmployeeChange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long changeId;

    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "changed_at")
    private LocalDateTime changedAt;

    @Column(name = "field_changed")
    private String fieldChanged;

    @Column(name = "old_value")
    private String oldValue;

    @Column(name = "new_value")
    private String newValue;

    // Constructors, Getters, and Setters

    public EmployeeChange() {}

    public EmployeeChange(Long employeeId, LocalDateTime changedAt, String fieldChanged, String oldValue, String newValue) {
        this.employeeId = employeeId;
        this.changedAt = changedAt;
        this.fieldChanged = fieldChanged;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    // Getters and Setters
    public Long getChangeId() {
        return changeId;
    }

    public void setChangeId(Long changeId) {
        this.changeId = changeId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDateTime getChangedAt() {
        return changedAt;
    }

    public void setChangedAt(LocalDateTime changedAt) {
        this.changedAt = changedAt;
    }

    public String getFieldChanged() {
        return fieldChanged;
    }

    public void setFieldChanged(String fieldChanged) {
        this.fieldChanged = fieldChanged;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    @Override
    public String toString() {
        return "EmployeeChange{" +
                "changeId=" + changeId +
                ", employeeId=" + employeeId +
                ", changedAt=" + changedAt +
                ", fieldChanged='" + fieldChanged + '\'' +
                ", oldValue='" + oldValue + '\'' +
                ", newValue='" + newValue + '\'' +
                '}';
    }
}
