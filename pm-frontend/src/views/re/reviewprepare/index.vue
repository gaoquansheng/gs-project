<template>
  <div class="main">
    <Navbar :active.sync="active" />
    <Reviewproject v-if="active == 0" />
    <Group v-if="active == 1" />
    <Groupexpert v-if="active == 2" />
    <Groupproject v-if="active == 3" />
    <Groupverify v-if="active == 4" />
    <Adjustexpert v-if="active == 5" />
    <Replaceexpert v-if="active == 6" />
  </div>
</template>




<script>
import Navbar from "@/views/re/reviewprepare/components/navbar";
import Reviewproject from "@/views/re/reviewprepare/components/reviewproject";
import Group from "@/views/re/reviewprepare/components/group";
import Groupexpert from "@/views/re/reviewprepare/components/groupexpert";
import Groupproject from "@/views/re/reviewprepare/components/groupproject";
import Groupverify from "@/views/re/reviewprepare/components/groupverify";
import Adjustexpert from "@/views/re/reviewprepare/components/adjustexpert";
import Replaceexpert from "@/views/re/reviewprepare/components/replaceexpert";
export default {
  name: "Step",
  components: {
    Navbar,
    Reviewproject,
    Group,
    Groupexpert,
    Groupproject,
    Groupverify,
    Adjustexpert,
    Replaceexpert,
  },
  data() {
    return {
      active: 0,
    };
  },

  methods: {},
};
</script>