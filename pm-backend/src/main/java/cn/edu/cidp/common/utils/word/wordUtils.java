package cn.edu.cidp.common.utils.word;

import cn.edu.cidp.pm.domain.Projectmeber;
import cn.edu.cidp.pm.domain.Projectorg;
import cn.edu.cidp.pm.domain.vo.ProjectbudgetVo;
import com.aspose.words.*;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class wordUtils {
    /**
     * @param mainDoc    主文档
     * @param addDoc     要拼接的文档
     * @param isPortrait 是否横向拼接
     * @Description ~~~
     * @Return com.aspose.words.Document 返回拼接后新的文档
     * @Author Mr.Walloce
     * @Date 2020年10月26日09:45:05
     */
    public static Document appendDocument(Document mainDoc, Document addDoc, boolean isPortrait) {
        getLicense();//获取授权信息
        String bookmark = "bm2";
        DocumentBuilder builder = null;
        try {
            builder = new DocumentBuilder(mainDoc);
            BookmarkCollection bms = mainDoc.getRange().getBookmarks();
            Bookmark bm = bms.get(bookmark);
            if (bm != null) {
                builder.moveToBookmark(bookmark, true, false);
                builder.writeln();
                builder.getPageSetup().setPaperSize(PaperSize.A4);
                Node insertAfterNode = builder.getCurrentParagraph().getPreviousSibling();
                insertDocumentAfterNode(insertAfterNode, mainDoc, addDoc);
            }
            //builder.insertBreak(BreakType.SECTION_BREAK_NEW_PAGE);
            if (isPortrait) {
                // 纵向纸张
                builder.getPageSetup().setOrientation(Orientation.PORTRAIT);
                //builder.insertBreak(BreakType.SECTION_BREAK_NEW_PAGE);
            } else {
                // 横向
                builder.getPageSetup().setOrientation(Orientation.LANDSCAPE);
            }
            builder.insertBreak(BreakType.SECTION_BREAK_NEW_PAGE);
            // builder.insertBreak(BreakType.PAGE_BREAK);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return mainDoc;
    }

    /**
     * @param mainDoc      主文档
     * @param tobeInserted 拼接的文档
     * @param bookmark     书签
     * @Description 向书签后插入文档
     * @Return com.aspose.words.Document
     * @Author Mr.Walloce
     * @Date 2019/7/27 18:33
     */
    public static Document insertDocumentAfterBookMark(Document mainDoc, Document tobeInserted, String bookmark)
            throws Exception {
        getLicense();//获取授权信息
        if (mainDoc == null) {
            return null;
        } else if (tobeInserted == null) {
            return mainDoc;
        } else {
            //构建新文档
            DocumentBuilder mainDocBuilder = new DocumentBuilder(mainDoc);
            if (bookmark != null && bookmark.length() > 0) {
                //获取到书签
                BookmarkCollection bms = mainDoc.getRange().getBookmarks();
                Bookmark bm = bms.get(bookmark);
                if (bm != null) {
                    mainDocBuilder.moveToBookmark(bookmark, true, false);
                    mainDocBuilder.writeln();
                    //获取到插入的位置
                    Node insertAfterNode = mainDocBuilder.getCurrentParagraph().getPreviousSibling();
                    insertDocumentAfterNode(insertAfterNode, mainDoc, tobeInserted);
                }
            } else {
                appendDoc(mainDoc, tobeInserted, true);
            }

            return mainDoc;
        }
    }

    /**
     * @param insertAfterNode 插入的位置
     * @param mainDoc
     * @param srcDoc
     * @Description TODO
     * @Return void
     * @Author Mr.Walloce
     * @Date 2019/7/27 14:51
     */
    public static void insertDocumentAfterNode(Node insertAfterNode, Document mainDoc, Document srcDoc)
            throws Exception {
        getLicense();//获取授权信息
        if (insertAfterNode.getNodeType() != 8 & insertAfterNode.getNodeType() != 5) {
            throw new Exception("The destination node should be either a paragraph or table.");
        } else {
            CompositeNode dstStory = insertAfterNode.getParentNode();

            while (null != srcDoc.getLastSection().getBody().getLastParagraph()
                    && !srcDoc.getLastSection().getBody().getLastParagraph().hasChildNodes()) {
                srcDoc.getLastSection().getBody().getLastParagraph().remove();
            }

            NodeImporter importer = new NodeImporter(srcDoc, mainDoc, 1);
            int sectCount = srcDoc.getSections().getCount();

            for (int sectIndex = 0; sectIndex < sectCount; ++sectIndex) {
                Section srcSection = srcDoc.getSections().get(sectIndex);
                int nodeCount = srcSection.getBody().getChildNodes().getCount();

                for (int nodeIndex = 0; nodeIndex < nodeCount; ++nodeIndex) {
                    Node srcNode = srcSection.getBody().getChildNodes().get(nodeIndex);
                    Node newNode = importer.importNode(srcNode, true);
                    dstStory.insertAfter(newNode, insertAfterNode);
                    insertAfterNode = newNode;
                }
            }

        }
    }

    /**
     * @param dstDoc
     * @param srcDoc
     * @param includeSection
     * @Description 文档拼接
     * @Return void
     * @Author Mr.Walloce
     * @Date 2019/7/27 14:53
     */
    public static void appendDoc(Document dstDoc, Document srcDoc, boolean includeSection) throws Exception {
        getLicense();//获取授权信息
        if (includeSection) {
            Iterator<Section> var3 = srcDoc.getSections().iterator();
            while (var3.hasNext()) {
                Section srcSection = (Section) var3.next();
                Node dstNode = dstDoc.importNode(srcSection, true, 0);
                dstDoc.appendChild(dstNode);
            }
        } else {
            Node node = dstDoc.getLastSection().getBody().getLastParagraph();
            if (node == null) {
                node = new Paragraph(srcDoc);
                dstDoc.getLastSection().getBody().appendChild(node);
            }

            if (node.getNodeType() != 8 & node.getNodeType() != 5) {
                throw new Exception("Use appendDoc(dstDoc, srcDoc, true) instead of appendDoc(dstDoc, srcDoc, false)");
            }

            insertDocumentAfterNode(node, dstDoc, srcDoc);
        }

    }

    /**
     * @Description 初始化文档并 向书签后插入内容
     */
    public static Document insertTextAfterBookMark(String fileName, String text, String bookmark)
            throws Exception {
        getLicense();//获取授权信息
        //构建新文档
        Document mainDoc = new Document(fileName);
        DocumentBuilder mainDocBuilder = new DocumentBuilder(mainDoc);
        if (bookmark != null && bookmark.length() > 0) {
            //获取到书签
            BookmarkCollection bms = mainDoc.getRange().getBookmarks();
            Bookmark bm = bms.get(bookmark);
            if (bm != null) {
                mainDocBuilder.moveToBookmark(bookmark, true, false);
                mainDocBuilder.write(text);
            }
            return mainDoc;
        } else {
            return mainDoc;
        }
    }

    /**
     * @Description 初始化文档并 向书签后插入内容
     */
    public static Document insertContentAfterBookMark(Document mainDoc, String text, String bookmark)
            throws Exception {
        getLicense();//获取授权信息
        //构建新文档
        DocumentBuilder mainDocBuilder = new DocumentBuilder(mainDoc);
        if (bookmark != null && bookmark.length() > 0) {
            //获取到书签
            BookmarkCollection bms = mainDoc.getRange().getBookmarks();
            Bookmark bm = bms.get(bookmark);
            if (bm != null) {
                mainDocBuilder.moveToBookmark(bookmark, true, false);
                mainDocBuilder.write(text);
            }
            return mainDoc;
        } else {
            return mainDoc;
        }
    }

    /**
     * @Description 初始化文档并 向书签后插入内容
     */
    public static Document insertTextAfterBookMark(Document mainDoc, String text, String bookmark)
            throws Exception {
        getLicense();//获取授权信息
        DocumentBuilder mainDocBuilder = new DocumentBuilder(mainDoc);
        if (bookmark != null && bookmark.length() > 0) {
            //获取到书签
            BookmarkCollection bms = mainDoc.getRange().getBookmarks();
            Bookmark bm = bms.get(bookmark);
            if (bm != null) {
                mainDocBuilder.moveToBookmark(bookmark, true, false);
                mainDocBuilder.write(text);
            }
            return mainDoc;
        } else {
            return mainDoc;
        }
    }

    /**
     * 生成表格预算表
     */
    public static Document generateBudget(Document document, List<ProjectbudgetVo> projectbudgetVoList, String bookMark) throws Exception {
        getLicense();//获取授权信息
        DocumentBuilder builder = new DocumentBuilder(document);
        builder.moveToBookmark(bookMark);
        //单元格水平居中对齐
        builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
        //段落居中对齐
        builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
//        builder.writeln("测试生成");
        //段落左对齐
        builder.getParagraphFormat().setAlignment(ParagraphAlignment.LEFT);
        builder.writeln("项目预算表");
        builder.startTable();
        //表头
        builder.insertCell();
        builder.write("预算科目名称");
        builder.insertCell();
        builder.write("合计");
        builder.insertCell();
        builder.write("专项");
        builder.insertCell();
        builder.write("自筹");
        builder.insertCell();
        builder.write("描述");
        builder.endRow();
        //表格内容
//        System.out.println(projectbudgetVoList);
        List<ProjectbudgetVo> data = getBudgetTree(projectbudgetVoList);
        System.out.println(data);
        System.out.println("========================================");
        for (int i = 0; i < data.size(); i++) {
            ProjectbudgetVo projectbudgetVo = data.get(i);
            if (projectbudgetVo.getChildrenBudget() != null) {
                builder.insertCell();
                builder.write(projectbudgetVo.getSubjectName());
                builder.insertCell();
                builder.write(projectbudgetVo.getTotalAmount() + "");
                builder.insertCell();
                builder.write(projectbudgetVo.getAppAmount() + "");
                builder.insertCell();
                builder.write(projectbudgetVo.getMyAmount() + "");
                builder.insertCell();
                if (projectbudgetVo.getReasonDesc() == null) {
                    builder.write("无描述");
                }
                builder.endRow();
                for (int j = 0; j < projectbudgetVo.getChildrenBudget().size(); j++) {
                    builder.insertCell();
                    builder.write("    (" + (j + 1) + ")" + projectbudgetVo.getChildrenBudget().get(j).getSubjectName());
                    builder.insertCell();
                    builder.write(projectbudgetVo.getTotalAmount() + "");
                    builder.insertCell();
                    builder.write(projectbudgetVo.getAppAmount() + "");
                    builder.insertCell();
                    builder.write(projectbudgetVo.getMyAmount() + "");
                    builder.insertCell();
                    if (projectbudgetVo.getReasonDesc() == null) {
                        builder.write("无描述");
                    }
                    builder.endRow();
                }
            } else {
                builder.insertCell();
                builder.write(projectbudgetVo.getSubjectName());
                builder.insertCell();
                builder.write(projectbudgetVo.getTotalAmount() + "");
                builder.insertCell();
                builder.write(projectbudgetVo.getAppAmount() + "");
                builder.insertCell();
                builder.write(projectbudgetVo.getMyAmount() + "");
                builder.insertCell();
                if (projectbudgetVo.getReasonDesc() == null) {
                    builder.write("无描述");
                }

                builder.endRow();
            }


        }
        builder.endTable();

        return document;
    }

    /**
     * 生成项目组成员
     */
    public static Document generateProjectMember(Document doc, List<Projectmeber> projectmebers, String bookMark) throws Exception {
        getLicense();//获取授权信息
        DocumentBuilder builder = new DocumentBuilder(doc);
        builder.moveToBookmark(bookMark);
        //单元格水平居中对齐
        builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
        //段落居中对齐
        builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
        //段落左对齐
        builder.getParagraphFormat().setAlignment(ParagraphAlignment.LEFT);
        builder.writeln("项目成员表");
        builder.startTable();
        //表头
        builder.insertCell();
        builder.write("姓名");
        builder.insertCell();
        builder.write("性别");
        builder.insertCell();
        builder.write("年龄");
        builder.insertCell();
        builder.write("专业");
        builder.insertCell();
        builder.write("任务分工");
        builder.insertCell();
        builder.write("所属部门");
        builder.endRow();
        //表格内容
        for (int i = 0; i < projectmebers.size(); i++) {
            Projectmeber projectmeber = projectmebers.get(i);
            builder.insertCell();
            builder.write(projectmeber.getUserName());

            builder.insertCell();
            builder.write(projectmeber.getSex() == "1" ? "男" : "女");

            builder.insertCell();
            builder.write(projectmeber.getAge() == null ? "0" : projectmeber.getAge().toString());

            builder.insertCell();
            builder.write(projectmeber.getMajor() == null ? "未知" : projectmeber.getMajor());

            builder.insertCell();
            builder.write(projectmeber.getTask() == null ? "未知": projectmeber.getTask());

            builder.insertCell();
            builder.write(projectmeber.getOrgName() == null ? "无" : projectmeber.getOrgName());

            builder.endRow();
        }
        builder.endTable();

        return doc;
    }

    /**
     * 生成项目合作单位
     */
    public static Document generateProjectOrg(Document doc, List<Projectorg> projectorgs, String bookMark) throws Exception {
        getLicense();//获取授权信息
        DocumentBuilder builder = new DocumentBuilder(doc);
        builder.moveToBookmark(bookMark);
        //单元格水平居中对齐
        builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
        //段落居中对齐
        builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
        //段落左对齐
        builder.getParagraphFormat().setAlignment(ParagraphAlignment.LEFT);
        builder.writeln("项目合作单位表");
        builder.startTable();
        //表头
        builder.insertCell();
        builder.write("单位名称");
        builder.insertCell();
        builder.write("联系人");
        builder.insertCell();
        builder.write("联系电话");
//        builder.insertCell();
        builder.endRow();
        //表格内容
        for (int i = 0; i < projectorgs.size(); i++) {
            Projectorg projectorg = projectorgs.get(i);
            builder.insertCell();

            builder.write(projectorg.getOrgName());
            builder.insertCell();

            builder.write(projectorg.getOrgAdminname() == null ? "无" : projectorg.getOrgAdminname());
            builder.insertCell();

            builder.write(projectorg.getOrgTel() == null ? "无" : projectorg.getOrgTel());
            builder.insertCell();


            builder.endRow();
        }
        builder.endTable();

        return doc;
    }

    /**
     * @param doc     需要被转换的word document格式
     * @param outPath 转换之后pdf的全路径带文件名
     * @Description TODO
     * @Return void
     * @Author Mr.Walloce
     * @Date 2019/7/27 13:47
     */
    public static void docToPdf(Document doc, String outPath) {
        try {
            //新建一个pdf文档
            File file = new File(outPath);
            FileOutputStream os = new FileOutputStream(file);

            //保存为pdf文件，saveFormat取的是words包下的，值为：40
            doc.save(os, com.aspose.words.SaveFormat.PDF);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取授权信息
     */
    public static void getLicense() {
        try {
            ClassPathResource classPathResource = new ClassPathResource("license.xml");
            InputStream inputStream = classPathResource.getInputStream();

//            InputStream inputStream = new FileInputStream(new File("lib/license.xml"));
            License aposeLicense = new License();
            aposeLicense.setLicense(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将有父子关系的数据转换成树形结构数据
     *
     * @return 最终的树状结构的集合数据
     */
    public static List<ProjectbudgetVo> getBudgetTree(List<ProjectbudgetVo> projectbudgetVoList) {
        // 获取数据库中带有有父子关系的数据
        List<ProjectbudgetVo> data = projectbudgetVoList;

        // 复制data数据
        List<ProjectbudgetVo> budgetTree = new ArrayList<>(data);
        // 遍历两次data来组装带有children关联性的对象，如果找到子级就删除menuList的数据
        for (ProjectbudgetVo entity : data) {
            for (ProjectbudgetVo entity2 : data) {
                //如果本级id与数据的父id相同，就说明是子父级关系
                if (entity.getSubjectId().equals(entity2.getParentSubjectId())) {
                    entity.getChildrenBudget().add(entity2);
                    budgetTree.remove(entity2);
                }
            }
        }
        return budgetTree;
    }
}
