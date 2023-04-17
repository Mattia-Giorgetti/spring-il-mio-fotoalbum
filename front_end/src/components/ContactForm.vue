<template>
  <form class="d-flex flex-column gap-3" @submit.prevent="sendMail()">
    <Transition name="bounce">
      <div v-if="success" class="alert alert-success">Mail Sent!</div>
    </Transition>

    <LoaderComponent v-if="store.loading" class="m-auto mb-5" />

    <label for="mail">Email</label>
    <input
      placeholder="insert your email here..."
      type="email"
      name="mail"
      id="mail"
      v-model="mail"
      :class="{ 'is-invalid': this.error }"
    />
    <p class="invalid-feedback">
      {{ error }}
    </p>

    <textarea
      placeholder="Write a message"
      name="message"
      id="message"
      cols="30"
      rows="5"
      v-model="message"
      :class="{ 'is-invalid': this.error }"
      class="mt-4"
    ></textarea>
    <p class="invalid-feedback">
      {{ error }}
    </p>

    <button type="submit" :disabled="store.loading" class="rounded-2">
      {{ store.loading ? "Sending..." : "Send" }}
    </button>
  </form>
</template>

<script>
import axios from "axios";
import { store } from "../store";
import LoaderComponent from "../components/LoaderComponent.vue";
export default {
  name: "ContactForm",
  components: { LoaderComponent },
  data() {
    return {
      store,
      mail: "",
      message: "",
      success: false,
      error: "",
    };
  },
  methods: {
    resetSuccess() {
      setTimeout(() => {
        this.success = false;
      }, 2000);
    },
    sendMail() {
      store.loading = true;
      const data = {
        mail: this.mail,
        message: this.message,
      };
      if (!this.mail || !this.message) {
        this.error = "This field is mandatory";
        store.loading = false;
        return;
      }

      axios
        .post(`${store.apiURL}mails`, data)
        .then((response) => {
          console.log(response.data);

          this.success = true;
          (this.mail = ""), (this.message = ""), (this.error = "");
          this.resetSuccess();
        })
        .catch((error) => {
          console.log(error);
        });
      store.loading = false;
    },
  },
};

// store.loading = false
</script>

<style lang="scss" scoped>
@use "../assets/styles/partials/variables" as *;

label {
  color: $primary-color;
  font-size: 1.2rem;
}
*::placeholder {
  color: gray;
  text-transform: capitalize;
  font-size: 0.9rem;
  font-style: italic;
}
input,
textarea {
  padding: 0.6rem;
}
button {
  width: 15%;
  margin-top: 1rem;
  background-color: $primary-color;
  border: none;
  padding: 0.4rem 0.8rem;
  color: $bg-color;
  transition: all 0.3s ease;
  &:hover {
    background-color: $secondary-color;
  }
}
.bounce-enter-active {
  animation: bounce-in 0.5s;
}
.bounce-leave-active {
  animation: bounce-in 0.5s reverse;
}
@keyframes bounce-in {
  0% {
    transform: scale(0);
  }
  50% {
    transform: scale(1.25);
  }
  100% {
    transform: scale(1);
  }
}
</style>
