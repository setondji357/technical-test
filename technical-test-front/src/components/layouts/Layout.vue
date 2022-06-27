<template>
  <div id="layout">
    <Header />
    <div class="main-content">
      <div class="container mt-3">
        <router-view />
      </div>
    </div>
    <Footer />
  </div>
</template>

<script>
import Footer from "./footer/footer";
import Header from "./header/header";
export default {
  name: "Layout",
  props: {
    msg: String,
  },
  components: {
    Header,
    Footer,
  },
};
</script>
