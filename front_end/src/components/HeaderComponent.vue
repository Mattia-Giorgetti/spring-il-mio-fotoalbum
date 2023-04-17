<template>
  <header class="py-3">
    <div class="container d-flex justify-content-between align-items-center">
      <div class="logo_spans">
        <i class="fa-solid fa-camera-retro fa-2xl"></i>
      </div>
      <nav class="d-sm-none d-lg-block">
        <ul
          class="list-unstyled d-flex align-items-center mb-0 gap-4 position-relative"
        >
          <li v-for="(item, index) in store.navbarLinks" :key="index">
            <router-link :to="{ name: item.url }" active-class="active-li">{{
              item.link
            }}</router-link>
          </li>
        </ul>
      </nav>
      <span
        class="d-lg-none hamburger_menu"
        @click="store.isDropDown = !store.isDropDown"
        ><i class="fa-solid fa-bars"></i
      ></span>
      <TransitionGroup name="nested">
        <div class="dropdown" v-if="store.isDropDown">
          <ul
            class="list-unstyled container d-flex flex-column align-items-end mb-0 gap-4 pt-2 pb-3 inner"
          >
            <li
              v-for="(item, index) in store.navbarLinks"
              :key="index"
              @click="!store.isDropDown"
            >
              <router-link :to="{ name: item.url }" active-class="active-li">{{
                item.link
              }}</router-link>
            </li>
          </ul>
        </div>
      </TransitionGroup>
    </div>
  </header>
</template>

<script>
import { store } from "../store";
export default {
  name: "HeaderComponent",
  data() {
    return {
      store,
    };
  },
};
</script>

<style lang="scss" scoped>
@use "../assets/styles/partials/variables" as *;

header {
  background-color: $primary-color;
  position: fixed;
  top: 0;
  width: 100%;
  z-index: 9999;

  .logo_spans {
    color: $bg-color;
    font-size: 1.5rem;
  }
  .active-li {
    color: $third-color;
    &:focus::after {
      animation-name: grow;
      animation-duration: 0.3s;
      animation-timing-function: linear;
      animation-fill-mode: forwards;
    }
  }
  .active-li::after {
    content: "";
    display: block;
    width: 0%;
    height: 2px;
    background-color: $third-color;
  }
  @keyframes grow {
    0% {
      width: 0;
    }
    100% {
      width: 100%;
    }
  }

  a {
    text-decoration: none;
    color: $bg-color;
    transition: all 0.3s ease;
    &:hover {
      opacity: 0.8;
    }
  }
  .hamburger_menu {
    color: $bg-color;
    font-size: 1.6rem;
    transition: all 0.3s ease;
    &:hover {
      color: $third-color;
      cursor: pointer;
    }
  }
  .dropdown {
    position: absolute;
    top: 4rem;
    right: 0;
    width: 100%;
    background-color: $primary-color;
  }
  .nested-enter-active,
  .nested-leave-active {
    transition: opacity 0.8s ease;
  }

  .nested-enter-from,
  .nested-leave-to {
    opacity: 0;
    transform: translateX(20px);
  }
  .nested-enter-active .inner,
  .nested-leave-active .inner {
    transition: all 0.3s ease-in-out;
  }
  .nested-enter-from .inner,
  .nested-leave-to .inner {
    transform: translateX(30px);
    opacity: 0;
  }
}
</style>
