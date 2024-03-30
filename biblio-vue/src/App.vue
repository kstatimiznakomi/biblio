<script setup lang="ts">
import Catalog from "./pages/Catalog.vue";
import {getBooks} from "./stores/Books";

export default {
  setup(){
    const booksStore = getBooks();
    booksStore.fetchItems()
  }
}
</script>



<template>
  <Catalog />
</template>

<style scoped>
@import './assets/biblio.css';
</style>
