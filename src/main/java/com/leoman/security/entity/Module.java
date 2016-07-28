package com.leoman.security.entity;

import com.leoman.common.entity.BaseEntity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Administrator on 2016/5/22.
 */
@Entity
@Table(name = "t_module")
public class Module extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;

    @Column(name = "description")
    private String description;

    @Column(name = "admin_id")
    private Long adminId;

    @OneToMany(mappedBy = "parent")
    private List<Module> childs;

    @ManyToOne
    @JoinTable(name = "t_module_relation", joinColumns = @JoinColumn(name = "parent_id"), inverseJoinColumns = @JoinColumn(name = "child_id"))
    private Module parent;

    public List<Module> getChilds() {
        return childs;
    }

    public void setChilds(List<Module> childs) {
        this.childs = childs;
    }

    public Module getParent() {
        return parent;
    }

    public void setParent(Module parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }
}
