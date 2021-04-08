<template>
  <div class="container">
    <h3>All Professors</h3>
    <table class="table">
      <thead>
        <tr>
          <th>Id</th>
          <th>First name</th>
          <th>Last name</th>
          <th>Email</th>
          <th>City</th>
          <th>Address</th>
          <th>Phone</th>
          <th>Reelection date</th>
          <th>Title</th>
          <th>Action</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="professor in professors" v-bind:key="professor.professorId">
          <td>{{ professor.professorId }}</td>
          <td>{{ professor.firstname }}</td>
          <td>{{ professor.lastname }}</td>
          <td>{{ professor.email }}</td>
          <td>{{ professor.city.cityName }}</td>
          <td>{{ professor.address }}</td>
          <td>{{ professor.phone }}</td>
          <td>{{ professor.reelectionDate }}</td>
          <td>{{ professor.title.titleName }}</td>
          <td>
            <div v-if="editProfessor === professor.professorId">
              <input v-model="professor.firstname" />
              <input v-model="professor.lastname" />
              <input v-model="professor.email" />
              <input v-model="professor.phone" />
              <input v-model="professor.address" />
              <select v-model="professor.city">
                <option
                  v-for="city in cities"
                  v-bind:key="city.cityId"
                  :value="city"
                >
                  {{ city.cityName }}
                </option>
              </select>
              <input type="date" v-model="professor.reelectionDate" />
              <select v-model="professor.title">
                <option
                  v-for="title in titles"
                  v-bind:key="title.titleId"
                  :value="title"
                >
                  {{ title.titleName }}
                </option>
              </select>

              <button
                v-on:click="updateProfessor(professor)"
                style="margin:5px"
                type="button"
                class="btn btn-primary"
              >
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  width="14"
                  height="14"
                  fill="currentColor"
                  class="bi bi-check"
                  viewBox="0 0 16 16"
                >
                  <path
                    d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"
                  />
                </svg>
              </button>

              <button
                class="btn btn-primary "
                v-on:click="editProfessor = null"
              >
                X
              </button>
            </div>
            <button
              class="btn btn-primary"
              style="margin-right:5px "
              v-on:click="editProfessor = professor.professorId"
            >
              Edit
            </button>
            <button
              class="btn btn-danger"
              style="margin-left:5px"
              v-on:click="deleteProfessor(professor)"
            >
              <b-icon- /> Delete
            </button>
          </td>
        </tr>
      </tbody>
    </table>
    <div>
      <button
        @click="goToAddProfessor()"
        class="btn btn-primary "
        style="margin-right:1050px"
      >
        Add new professor
      </button>
    </div>
  </div>
</template>
<script>
import axios from "axios";
import http from "../http-common";
export default {
  name: "ProfessorComponent",
  data() {
    return {
      cities: [],
      titles: [],
      professors: [],
      editProfessor: null,
    };
  },
  created() {
    this.professorList();
    this.cityList();
    this.titlesList();
  },
  methods: {
    cityList() {
      http
        .get("api/getCities")
        .then((response) => {
          this.cities = response.data;
        })
        .catch((err) => console.log("Error while displaying cities" + err));
    },
    titlesList() {
      http
        .get("api/getTitles")
        .then((response) => {
          this.titles = response.data;
        })
        .catch((err) => console.log("Error while displaying titles" + err));
    },
    professorList() {
      axios
        .get("http://localhost:8080/professor/getProfessors", {})
        .then((response) => {
          this.professors = response.data;
        })
        .catch((err) =>
          console.log("Error while displaying professors " + err)
        );
    },
    deleteProfessor: async function(professor) {
      await axios.delete(
        "http://localhost:8080/professor/" + professor.professorId
      );
      window.location.reload();

      this.subjectList();
    },
    updateProfessor(professor) {
      fetch("http://localhost:8080/professor/" + professor.professorId, {
        body: JSON.stringify(professor),
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
      }).then(() => {
        this.editProfessor = null;
      });
    },

    goToAddProfessor() {
      this.$router.push("/addProfessor");
    },
  },
};
</script>

<style scoped>
.dodavanje {
  padding-top: 20px;
}
input {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}
</style>
