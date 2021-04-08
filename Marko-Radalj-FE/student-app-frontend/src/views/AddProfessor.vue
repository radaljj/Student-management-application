<template>
  <div class="submitform">
    <div v-if="!submitted">
      <div class="form-group">
        <label for="name">Professor name</label>
        <input
          type="text"
          class="form-control"
          id="name"
          required
          v-model="professor.firstname"
          name="name"
        />
      </div>
      <div class="form-group">
        <label for="description">Professor last name</label>
        <input
          type="text"
          class="form-control"
          id="naDescriptionme"
          required
          v-model="professor.lastname"
          name="lastname"
        />
      </div>

      <div class="form-group">
        <label for="esp">Professor address</label>
        <input
          type="text"
          class="form-control"
          id="esp"
          required
          v-model="professor.address"
          name="address"
        />
      </div>

      <div class="form-group">
        <label for="semester">Professor phone</label>
        <input
          type="text"
          class="form-control"
          id="semester"
          required
          v-model="professor.phone"
          name="semester"
        />
      </div>
      <div class="form-group">
        <label for="yearOfStudy">Professor email:</label>
        <input
          type="text"
          class="form-control"
          id="yearOfStudy"
          required
          v-model="professor.email"
          name="yearOfStudy"
        />
      </div>
      <div class="form-group">
        <label for="esp">Reelection date</label>
        <input
          type="date"
          class="form-control"
          id="esp"
          required
          v-model="professor.reelectionDate"
          name="date"
        />
      </div>

      <div class="form-group">
        <label style="margin:15px">List of cities</label>
        <select v-model="professor.city">
          <option v-for="city in cities" v-bind:key="city.cityId" :value="city">
            {{ city.cityName }}
          </option>
        </select>
      </div>

      <div class="form-group">
        <label style="margin:15px">List of titles</label>
        <select v-model="professor.title">
          <option
            v-for="title in titles"
            v-bind:key="title.titleId"
            :value="title"
          >
            {{ title.titleName }}
          </option>
        </select>
      </div>
      <button
        v-on:click="saveProfessor"
        class="btn btn-success"
        style="margin-top:12px"
      >
        Submit
      </button>
    </div>

    <div v-else>
      <h3>You submitted successfully!</h3>
      <button class="btn btn-success" v-on:click="newProfessor">
        Add another professor
      </button>
      <button
        @click="goToProfessor()"
        class="btn btn-primary"
        style="margin-top:12px"
      >
        List of professors
      </button>
    </div>
  </div>
</template>

<script>
import http from "../http-common";
export default {
  name: "add-professor",
  data() {
    return {
      titles: [],
      cities: [],
      professor: {
        professorId: 0,
        firstname: "",
        lastname: "",
        email: "",
        address: "",
        phone: "",
        reelectionDate: "",
        title: 0,
        city: 0,
      },

      submitted: false,
    };
  },
  created() {
    this.cityList();
    this.cityTitles();
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
    cityTitles() {
      http
        .get("api/getTitles")
        .then((response) => {
          this.titles = response.data;
        })
        .catch((err) => console.log("Error while displaying titles" + err));
    },
    saveProfessor() {
      var data = {
        professorId: this.professor.professorId,
        firstname: this.professor.firstname,
        lastname: this.professor.lastname,
        email: this.professor.email,
        address: this.professor.address,
        phone: this.professor.phone,
        reelectionDate: this.professor.reelectionDate,
        city: this.professor.city,
        title: this.professor.title,
      };

      http
        .post("/professor", data)
        .then((response) => {
          this.professor.professorId = response.data.professorId;
          this.professor.city = response.data.city;
          this.professor.title = response.data.title;
          console.log(response.data.professorId);
        })
        .catch((e) => {
          console.log(e);
        });

      this.submitted = true;
    },
    goToProfessor() {
      this.$router.push("/professors");
    },
    newProfessor() {
      this.submitted = false;
      this.professor = {};
    },
  },
};
</script>
<style>
select {
  margin: 50px;
  width: 150px;
  padding: 5px 35px 5px 5px;
  font-size: 16px;
  border: 1px solid #ccc;
  height: 34px;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  background: url(https://www.eng.it/resources/images/logo%20eng.png) 96% / 15%
    no-repeat #eee;
}
.submitform {
  max-width: 300px;
  margin: auto;
}
#dropdown {
  background: #fff;
  border-radius: 4px;
  padding: 20px;
  transition: all 0.2s;
}
</style>
