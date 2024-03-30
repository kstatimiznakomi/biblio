<template>
  <section class = "head sticky-top">
    <header class="header justify-content-between">
      <RouterLink to="/" /><img src="https://i.postimg.cc/9FpWWbkr/rd-lib.png" height="40" width="40"/>
      <nav>
        <ul class = "menu">
          <li><RouterLink to="/"/>Главная</li>
          <li><RouterLink to="/catalog"/>Каталог</li>
          <li><RouterLink to="/about"/>О нас</li>
          <li><RouterLink to="/profile"/>Профиль</li>
        </ul>
      </nav>
      <div class = "btn__menu">
      </div>
      <div class = "btn__menu">
      </div>
    </header>
  </section>
</template>

<script>

</script>

<style scoped>
@import '@/assets/biblio.css';
</style>