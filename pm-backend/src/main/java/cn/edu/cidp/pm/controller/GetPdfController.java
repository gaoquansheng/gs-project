package cn.edu.cidp.pm.controller;


import cn.edu.cidp.common.constant.Constants;
import cn.edu.cidp.common.utils.StringUtils;
import cn.edu.cidp.common.utils.file.FileUtils;
import cn.edu.cidp.common.utils.word.wordUtils;
import cn.edu.cidp.framework.config.CIDPConfig;
import cn.edu.cidp.framework.config.ServerConfig;
import cn.edu.cidp.framework.web.domain.AjaxResult;
import cn.edu.cidp.pc.domain.Projectcategoryphase;
import cn.edu.cidp.pc.service.IProjectcategoryphaseService;
import cn.edu.cidp.pm.domain.*;
import cn.edu.cidp.pm.domain.vo.ProjectbudgetVo;
import cn.edu.cidp.pm.service.*;
import com.aspose.words.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pm/pdf")
public class GetPdfController {
    @Autowired
    private ServerConfig serverConfig;

    @Autowired
    private IProjectListService projectListService;

    @Autowired
    private IProjecttaskService projecttaskService;

    @Autowired
    private IProjectconclusionreportService projectconclusionreportService;

    @Autowired
    private IProjectbudgetService projectbudgetService;

    @Autowired
    private IProjectwordcontentService projectwordcontentService;

    @Autowired
    private IProjectmeberService projectmeberService;

    @Autowired
    private IProjectorgService projectorgService;


    @Autowired
    private IProjectcategoryphaseService projectcategoryphaseService;

    @GetMapping("/make/{projectId}/{phaseCode}/{categoryCode}")
    public void generatePdf(@PathVariable("projectId") Long projectId, @PathVariable("phaseCode") String phaseCode, @PathVariable("categoryCode") String categoryCode, HttpServletRequest request, HttpServletResponse response) throws Exception {

        if(phaseCode.substring(0,1).equals("1")){
//            System.out.println(categoryCode + " " + phaseCode + "" + " ????????????");
            String wordTemplate = projectcategoryphaseService.selectProjectcategoryphaseById(categoryCode, phaseCode).getWordPath();
//            System.out.println(wordTemplate);
            String wordTemplatePath = cn.edu.cidp.pm.controller.GetPdfController.realPath(wordTemplate);
            wordUtils.getLicense();

            Document mainDoc = new Document(wordTemplatePath);

            //??????project
            ProjectList project = projectListService.selectprojectListById(projectId);
            mainDoc = wordUtils.insertContentAfterBookMark(mainDoc,project.getProjectName(),"projectName");
            mainDoc = wordUtils.insertContentAfterBookMark(mainDoc,project.getCategoryCode(),"categoryCode");
            mainDoc = wordUtils.insertContentAfterBookMark(mainDoc,project.getUserName(),"userName");
            mainDoc = wordUtils.insertContentAfterBookMark(mainDoc,project.getKeyWords(),"keyWords");
            //??????????????????
            List<ProjectbudgetVo> projectbudgetVo = new ArrayList<>(projectbudgetService.selectProjectbudgetList(phaseCode, projectId, categoryCode));
            mainDoc = wordUtils.generateBudget(mainDoc, projectbudgetVo, "projectBudget");
            //?????????????????????
            List<Projectmeber> projectmebers = new ArrayList<>(projectmeberService.selectProjectmeberList(projectId, phaseCode));
            mainDoc = wordUtils.generateProjectMember(mainDoc, projectmebers, "projectMember");
            //????????????????????????
            List<Projectorg> projectorgs = new ArrayList<>(projectorgService.selectProjectorgList(projectId, phaseCode));
            mainDoc = wordUtils.generateProjectOrg(mainDoc, projectorgs, "projectOrg");
            //????????????
            List<Projectwordcontent> projectwordcontentList = new ArrayList<>(projectwordcontentService.selectProjectwordcontentList(projectId, phaseCode));

            for (int i = 0; i < projectwordcontentList.size(); i++) {
                if (projectwordcontentList.get(i).getAttachedWordPath().equals("")) {
                    mainDoc = wordUtils.insertTextAfterBookMark(mainDoc, projectwordcontentList.get(i).getTextContent(), projectwordcontentList.get(i).getBookmarkCode());
                } else {
                    String wordPath = projectwordcontentList.get(i).getAttachedWordPath();
                    System.out.println(wordPath);
                    // ??????????????????
                    String localPath = CIDPConfig.getProfile();
                    // ?????????????????????
                    String downloadPath = localPath + StringUtils.substringAfter(wordPath, Constants.RESOURCE_PREFIX);
                    System.out.println(downloadPath);
                    wordUtils.getLicense();
                    Document addDoc = new Document(downloadPath);
                    mainDoc = wordUtils.insertDocumentAfterBookMark(mainDoc, addDoc, projectwordcontentList.get(i).getBookmarkCode());
                }
            }
            //    xxxxx/x/x/   upload/project/name.pdf
            String outPath = CIDPConfig.getUploadPath()+ File.separator + "project" + File.separator + project.getProjectName() +".pdf";
            File exits = new File(CIDPConfig.getUploadPath()+ File.separator + "project");
            if (!exits.exists() && !exits.isDirectory()) {
                System.out.println("//?????????");
                exits.mkdir();
            }
            String httpPath = serverConfig.getUrl()+ Constants.RESOURCE_PREFIX + "/" + "upload" + "/" + "project" + "/" + project.getProjectName() +".pdf";
            project.setPdfPath(httpPath);
            projectListService.updateprojectList(project);
            wordUtils.docToPdf(mainDoc, outPath);
            String pdfPath = outPath;
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;fileName=");
            FileUtils.writeBytes(pdfPath, response.getOutputStream());
            File file = new File(pdfPath);
            if (file.exists()) {
                DataOutputStream temps = new DataOutputStream(response.getOutputStream());
                DataInputStream in = new DataInputStream(new FileInputStream(pdfPath));
                byte[] b = new byte[2048];
                while ((in.read(b)) != -1) {
                    temps.write(b);
                    temps.flush();
                }
                in.close();
                temps.close();
            } else {
                System.out.printf("a");
            }
        }
        if(phaseCode.substring(0,1).equals("2")){
            System.out.println(categoryCode + " " + phaseCode + "" + " ????????????");
            String wordTemplate = projectcategoryphaseService.selectProjectcategoryphaseById(categoryCode, phaseCode).getWordPath();
            System.out.println(wordTemplate);
            String wordTemplatePath = cn.edu.cidp.pm.controller.GetPdfController.realPath(wordTemplate);
            //??????project
            Projecttask projecttask = projecttaskService.selectProjecttaskById(projectId);
            Document mainDoc = wordUtils.insertTextAfterBookMark(wordTemplatePath, projecttask.getProjectName(), "projectName");
            //??????????????????
            List<ProjectbudgetVo> projectbudgetVo = new ArrayList<>(projectbudgetService.selectProjectbudgetList(phaseCode, projectId, categoryCode));
            mainDoc = wordUtils.generateBudget(mainDoc, projectbudgetVo, "projectBudget");
            //?????????????????????
            List<Projectmeber> projectmebers = new ArrayList<>(projectmeberService.selectProjectmeberList(projectId, phaseCode));
            mainDoc = wordUtils.generateProjectMember(mainDoc, projectmebers, "projectMember");
            //????????????????????????
            List<Projectorg> projectorgs = new ArrayList<>(projectorgService.selectProjectorgList(projectId, phaseCode));
            mainDoc = wordUtils.generateProjectOrg(mainDoc, projectorgs, "projectOrg");
            //????????????
            List<Projectwordcontent> projectwordcontentList = new ArrayList<>(projectwordcontentService.selectProjectwordcontentList(projectId, phaseCode));

            for (int i = 0; i < projectwordcontentList.size(); i++) {
                if (projectwordcontentList.get(i).getAttachedWordPath().equals("")) {
                    mainDoc = wordUtils.insertTextAfterBookMark(mainDoc, projectwordcontentList.get(i).getTextContent(), projectwordcontentList.get(i).getBookmarkCode());
                } else {
                    String wordPath = projectwordcontentList.get(i).getAttachedWordPath();
                    System.out.println(wordPath);
                    // ??????????????????
                    String localPath = CIDPConfig.getProfile();
                    // ?????????????????????
                    String downloadPath = localPath + StringUtils.substringAfter(wordPath, Constants.RESOURCE_PREFIX);
                    System.out.println(downloadPath);
                    wordUtils.getLicense();
                    Document addDoc = new Document(downloadPath);
                    mainDoc = wordUtils.insertDocumentAfterBookMark(mainDoc, addDoc, projectwordcontentList.get(i).getBookmarkCode());
                }
            }

            //??????????????? ???????????????????????????
            String outPath = CIDPConfig.getUploadPath() + File.separator + "projecttask" + File.separator + projecttask.getProjectName() +".pdf";
            File exits = new File(CIDPConfig.getUploadPath() + File.separator + "projecttask");
            if (!exits.exists() && !exits.isDirectory()) {
                System.out.println("//?????????");
                exits.mkdir();
            }

            //??????????????????????????????
            String httpPath = serverConfig.getUrl()+ Constants.RESOURCE_PREFIX + "/" + "upload" + "/" + "projecttask" + "/" + projecttask.getProjectName() +".pdf";
            projecttask.setPdfPath(httpPath);
            projecttaskService.updateProjecttask(projecttask);

            //??????pdf
            wordUtils.docToPdf(mainDoc, outPath);
            String pdfPath = outPath;
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;fileName=");
            FileUtils.writeBytes(pdfPath, response.getOutputStream());
            File file = new File(pdfPath);
            if (file.exists()) {
                DataOutputStream temps = new DataOutputStream(response.getOutputStream());
                DataInputStream in = new DataInputStream(new FileInputStream(pdfPath));
                byte[] b = new byte[2048];
                while ((in.read(b)) != -1) {
                    temps.write(b);
                    temps.flush();
                }
                in.close();
                temps.close();
            } else {
                System.out.printf("a");
            }
        }
        if(phaseCode.substring(0,1).equals("4")){
            System.out.println(categoryCode + " " + phaseCode + "" + " ????????????");
            String wordTemplate = projectcategoryphaseService.selectProjectcategoryphaseById(categoryCode, phaseCode).getWordPath();
            System.out.println(wordTemplate);
            String wordTemplatePath = cn.edu.cidp.pm.controller.GetPdfController.realPath(wordTemplate);
            //??????project
            Projectconclusionreport projectconclusionreport = projectconclusionreportService.selectProjectconclusionreportById(projectId);
            Document mainDoc = wordUtils.insertTextAfterBookMark(wordTemplatePath, projectconclusionreport.getProjectName(), "projectName");
            //??????????????????
            List<ProjectbudgetVo> projectbudgetVo = new ArrayList<>(projectbudgetService.selectProjectbudgetList(phaseCode, projectId, categoryCode));
            mainDoc = wordUtils.generateBudget(mainDoc, projectbudgetVo, "projectBudget");
            //?????????????????????
            List<Projectmeber> projectmebers = new ArrayList<>(projectmeberService.selectProjectmeberList(projectId, phaseCode));
            mainDoc = wordUtils.generateProjectMember(mainDoc, projectmebers, "projectMember");
            //????????????????????????
            List<Projectorg> projectorgs = new ArrayList<>(projectorgService.selectProjectorgList(projectId, phaseCode));
            mainDoc = wordUtils.generateProjectOrg(mainDoc, projectorgs, "projectOrg");
            //????????????
            List<Projectwordcontent> projectwordcontentList = new ArrayList<>(projectwordcontentService.selectProjectwordcontentList(projectId, phaseCode));

            for (int i = 0; i < projectwordcontentList.size(); i++) {
                if (projectwordcontentList.get(i).getAttachedWordPath().equals("")) {
                    mainDoc = wordUtils.insertTextAfterBookMark(mainDoc, projectwordcontentList.get(i).getTextContent(), projectwordcontentList.get(i).getBookmarkCode());
                } else {
                    String wordPath = projectwordcontentList.get(i).getAttachedWordPath();
                    System.out.println(wordPath);
                    // ??????????????????
                    String localPath = CIDPConfig.getProfile();
                    // ?????????????????????
                    String downloadPath = localPath + StringUtils.substringAfter(wordPath, Constants.RESOURCE_PREFIX);
                    System.out.println(downloadPath);
                    wordUtils.getLicense();
                    Document addDoc = new Document(downloadPath);
                    mainDoc = wordUtils.insertDocumentAfterBookMark(mainDoc, addDoc, projectwordcontentList.get(i).getBookmarkCode());
                }
            }

            String outPath = CIDPConfig.getUploadPath() + File.separator + "projectreport" + File.separator + projectconclusionreport.getProjectName() +".pdf";
            File exits = new File(CIDPConfig.getUploadPath() + File.separator + "projectreport");
            if (!exits.exists() && !exits.isDirectory()) {
                System.out.println("//?????????");
                exits.mkdir();
            }

            String httpPath = serverConfig.getUrl() + Constants.RESOURCE_PREFIX + "/" + "upload" + "/" + "projectreport" + "/" + projectconclusionreport.getProjectName() +".pdf";
            projectconclusionreport.setPdfPath(httpPath);
            projectconclusionreportService.updateProjectconclusionreport(projectconclusionreport);


            wordUtils.docToPdf(mainDoc, outPath);
            String pdfPath = outPath;

            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;fileName=");
            FileUtils.writeBytes(pdfPath, response.getOutputStream());
            File file = new File(pdfPath);
            if (file.exists()) {
                DataOutputStream temps = new DataOutputStream(response.getOutputStream());
                DataInputStream in = new DataInputStream(new FileInputStream(pdfPath));
                byte[] b = new byte[2048];
                while ((in.read(b)) != -1) {
                    temps.write(b);
                    temps.flush();
                }
                in.close();
                temps.close();
            } else {
                System.out.printf("a");
            }
        }
    }


    /**
     * ??????????????????????????????????????????????????????
     *
     * @param dataBasePath
     */
    public static String realPath(String dataBasePath) {
        String localPath = CIDPConfig.getProfile();
        String realPath = localPath + StringUtils.substringAfter(dataBasePath, Constants.RESOURCE_PREFIX);
        return realPath;
    }
}
