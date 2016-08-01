package com.leoman.task.controller;

import com.leoman.common.controller.common.GenericEntityController;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.image.entity.FileBo;
import com.leoman.task.entity.Task;
import com.leoman.task.service.TaskService;
import com.leoman.task.service.impl.TaskServiceImpl;
import com.leoman.utils.FileUtil;
import com.leoman.utils.JsonUtil;
import com.leoman.utils.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * 任务
 * Created by Administrator on 2016/7/28.
 */
@Controller
@RequestMapping(value = "/admin/task")
public class TaskController extends GenericEntityController<Task,Task,TaskServiceImpl>{

    @Autowired
    private TaskService taskService;

    /**
     * 益起来-任务活动列表页面跳转
     * @return
     */
    @RequestMapping(value = "/yql/index")
    public String yqlIndex(){
        return "task/yql_list";
    }

    /**
     * 脑洞虚设-任务活动列表页面跳转
     * @return
     */
    @RequestMapping(value = "/ndxs/index")
    public String ndxsIndex(){
        return "task/ndxs_list";
    }

    /**
     * 任务活动列表
     * @param task
     * @param start
     * @param length
     * @param draw
     * @param taskStatus
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Map<String, Object> list(Task task, Integer start, Integer length, Integer draw,Integer taskStatus,Integer checkpointStatus) {
        int currentPage = getPageNum(start, length);
        Page<Task> page = taskService.findPage(task,taskStatus,checkpointStatus, currentPage, length);
        return DataTableFactory.fitting(draw, page);
    }

    /**
     * 详情页面
     * @param id
     * @param type
     * @param model
     * @return
     */
    @RequestMapping(value = "/detail")
    public String detail(Long id,Integer type,Model model){
        Task task = taskService.queryByPK(id);
        model.addAttribute("task",task);
        if(type==1){
            return "task/yql_detail";
        }
        if(type==2){
            return "task/ndxs_detail";
        }
        return null;
    }

    /**
     * 跳转新增页面
     * @param type
     * @return
     */
    @RequestMapping(value = "/add")
    public String add(Integer type){
        if(type==1){
            return "task/yql_add";
        }
        if(type==2){
            return "task/ndxs_add";
        }
        return null;
    }

    /**
     * 益起来-新增
     * @param task
     * @param imageFile
     * @param detail
     * @return
     */
    @RequestMapping(value = "/yql/save")
    @ResponseBody
    public Result yqlSave(Task task, @RequestParam(value = "imageFile",required = false) MultipartFile imageFile, String detail){
        try{
            this.save(task,imageFile,detail);
            //益起来
            task.setType(1);
            //状态可用
            task.setStatus(0);
            taskService.save(task);
        }catch (RuntimeException e){
            e.printStackTrace();
            return Result.failure();
        }
        return Result.success();
    }

    /**
     * 脑洞虚设-新增
     * @param task
     * @param imageFile
     * @param detail
     * @return
     */
    @RequestMapping(value = "/ndxs/save")
    @ResponseBody
    public Result ndxsSave(Task task, @RequestParam(value = "imageFile",required = false) MultipartFile imageFile, String detail){
        try{
            this.save(task,imageFile,detail);
            //脑洞虚设
            task.setType(2);
            //个人
            task.setJoinType(0);
            taskService.save(task);
        }catch (RuntimeException e){
            e.printStackTrace();
            return Result.failure();
        }
        return Result.success();
    }

    /**
     * 改变状态 是否可用
     * @param id
     * @param status
     * @return
     */
    @RequestMapping(value = "/status")
    @ResponseBody
    public Result status(Long id, Integer status){
        Task task = taskService.queryByPK(id);
        try{
            task.setStatus(status);
            taskService.update(task);
        }catch (RuntimeException e){
            e.printStackTrace();
            return Result.failure();
        }
        return Result.success();
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @RequestMapping(value = "/batchDel", method = RequestMethod.POST)
    @ResponseBody
    public Result batchDel(String ids) {
        if (StringUtils.isBlank(ids)){
            return Result.failure();
        }
        try {
            Long[] ss = JsonUtil.json2Obj(ids,Long[].class);
            for (Long id : ss) {
                taskService.delete(taskService.queryByPK(id));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure();
        }
        return Result.success();
    }


    private void save(Task task,MultipartFile imageFile, String detail){
        if(imageFile!=null && imageFile.getSize()>0) {
            FileBo fileBo = null;
            try {
                fileBo = FileUtil.save(imageFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (fileBo != null && StringUtils.isNotBlank(fileBo.getPath())) {
                task.setCoverUrl(fileBo.getPath());
            }
        }
        if (detail != null) {
            task.setDetail(detail.replace("&lt", "<").replace("&gt", ">"));
        }

    }



}
