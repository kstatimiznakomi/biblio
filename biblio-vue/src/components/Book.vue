<template>
  <div class="book-bg">
    <ul class="book" style="margin-left: 0px; margin-right: 0px;">
      <img class="book-img" :src="img" alt="BookImg">
      <li style="margin-right: 0px;"/>
        <div class="name-book">
          <a href="book/{{ id }}"/>{{ bookName }}
          <span/>Количество: {{ count }}
        </div>
    </ul>
  </div>
</template>

<script setup>
defineProps({
  id: Number,
  bookName: String,
  img: String,
  count: Number
})
const ifSigned = ref(false)
</script>

<style scoped>
@import '@/assets/biblio.css';
</style>