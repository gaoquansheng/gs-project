<template>
  <!-- 预览提交 -->
  <div class="application">
    <p class="arrow">
      <span
        @click="changePdfPage(0)"
        class="turn"
        :class="{ grey: currentPage == 1 }"
        >Preview</span
      >
      {{ currentPage }} / {{ pageCount }}
      <span
        @click="changePdfPage(1)"
        class="turn"
        :class="{ grey: currentPage == pageCount }"
        >Next</span
      >
    </p>
    <pdf
      :src="src"
      :page="currentPage"
      @num-pages="pageCount = $event"
      @page-loaded="currentPage = $event"
      @loaded="loadPdfHandler"
    />
  </div>
</template>
<script>
import { getPdf } from "@/api/pm/step.js";
import pdf from "vue-pdf";
export default {
    components: { pdf },
  data() {
    return {
      src: "",
      currentPage: 0, // pdf文件页码
      pageCount: 0, // pdf文件总页
      params: this.initParams,
    };
  },
  props: {
    initParams: {
      type: Object,
    },
  },
  mounted() {
    getPdf(
      this.params.projectId,
      this.params.projectStatus,
      this.params.categoryCode
    ).then((res) => {
      // console.log(res);
      this.src = this.getObjectURL(res);
    });
  },
  methods: {
    // 将返回的流数据转换为url
    getObjectURL(file) {
      let url = null;
      if (window.createObjectURL != undefined) {
        // basic
        url = window.createObjectURL(file);
      } else if (window.webkitURL != undefined) {
        // webkit or chrome
        try {
          url = window.webkitURL.createObjectURL(file);
        } catch (error) {}
      } else if (window.URL != undefined) {
        // mozilla(firefox)
        try {
          url = window.URL.createObjectURL(file);
        } catch (error) {}
      }
      return url;
    },
    // 改变PDF页码,val传过来区分上一页下一页的值,0上一页,1下一页
    changePdfPage(val) {
      // console.log(val)
      if (val === 0 && this.currentPage > 1) {
        this.currentPage--;
        // console.log(this.currentPage)
      }
      if (val === 1 && this.currentPage < this.pageCount) {
        this.currentPage++;
        // console.log(this.currentPage)
      }
    },

    // pdf加载时
    loadPdfHandler(e) {
      this.currentPage = 1; // 加载的时候先加载第一页
    },
  },
};
</script>