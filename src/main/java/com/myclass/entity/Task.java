package com.myclass.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Author: Nguyễn Chánh Trực
 * Created: 12/25/2020
 * Updated: 12/25/2020
 */
@Entity
@Data
@ToString
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "job_id")
    private int jobId;
    @Column(name = "status_id")
    private int statusId;

    @ManyToOne
    @JoinColumn(name = "job_id",insertable = false,updatable = false)
    private Job job;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "status_id", insertable = false, updatable = false)
    private Status status;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Task that = (Task) obj;
        return ( this.name.equals(that.name) &&
                this.startDate.equals(that.startDate) &&
                this.endDate.equals(that.endDate) &&
                this.id == that.id &&
                this.statusId == that.statusId &&
                this.userId == that.userId &&
                this.jobId == that.jobId
        );
    }
}
