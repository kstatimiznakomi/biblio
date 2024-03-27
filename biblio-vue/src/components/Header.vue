<template>
  <section class = "head sticky-top">
    <header class="header justify-content-between">
      <a href="#"><img src="https://i.postimg.cc/9FpWWbkr/rd-lib.png" height="40" width="40"></a>
      <nav>
        <ul class = "menu">
          <li><RouterLink to="/"/>Главная</li>
          <li><RouterLink to="/catalog"/>Каталог</li>
          <li><RouterLink to="/about"/>О нас</li>
          <li><RouterLink to="/profile"/>Профиль</li>
        </ul>
      </nav>
      <div class = "btn__menu">
        <span th:text="${user.username}"></span>
        <a th:href="@{/logout}">Выйти</a>
      </div>
      <div class = "btn__menu">
        <a th:href="@{/login}">Войти</a>
        <a th:href="@{/register}" style = "margin-left: 0px; margin-right: 0px;">Регистрация</a>
      </div>
    </header>
  </section>
</template>

<script>

</script>

<style scoped>

</style>