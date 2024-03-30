<template>
  <section class="flex-container">
    <div id="content">
      this is content
      <Book v-for="item in get" />
    </div>
  </section>
</template>

<script setup>
import Book from '../components/Book.vue';

export default defineComps({
  components: { Book },
  data() {
    return {
      searchValue: "",
    };
  },
  computed: {

  }
})
</script>

<style scoped>
@import '../assets/biblio.css';
</style>