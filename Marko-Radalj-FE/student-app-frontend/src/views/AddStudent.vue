<template>
  <div class="submitform">
    <div v-if="!submitted">
      <div class="form-group">
        <label for="name">Student name</label>
        <input
          type="text"
          class="form-control"
          id="name"
          required
          v-model="student.firstname"
          name="name"
        />
      </div>
      <div class="form-group">
        <label for="description">Student last name</label>
        <input
          type="text"
          class="form-control"
          id="naDescriptionme"
          required
          v-model="student.lastname"
          name="lastname"
        />
      </div>

      <div class="form-group">
        <label for="esp">Student address</label>
        <input
          type="text"
          class="form-control"
          id="esp"
          required
          v-model="student.address"
          name="address"
        />
      </div>

      <div class="form-group">
        <label for="semester">Index number</label>
        <input
          type="number"
          class="form-control"
          id="semester"
          required
          v-model="student.indexNumber"
          name="semester"
        />
      </div>
      <div class="form-group">
        <label for="yearOfStudy">Student email:</label>
        <input
          type="text"
          class="form-control"
          id="yearOfStudy"
          required
          v-model="student.email"
          name="yearOfStudy"
        />
      </div>
      <div class="form-group">
        <label for="esp">Index year</label>
        <input
          type="number"
          class="form-control"
          id="esp"
          required
          v-model="student.indexYear"
          name="year"
        />
      </div>

      <div class="form-group">
        <label for="esp">Current study year</label>
        <input
          type="number"
          class="form-control"
          id="esp"
          required
          v-model="student.currentYearOfStudy"
          name="studyYear"
        />
      </div>

      <div class="form-group">
        <label style="margin:15px">List of cities</label>
        <select v-model="student.city">
          <option v-for="city in cities" v-bind:key="city.cityId" :value="city">
            {{ city.cityName }} - {{ city.cityId }}
          </option>
        </select>
      </div>
      <button
        v-on:click="saveStudent"
        class="btn btn-success"
        style="margin-top:12px"
      >
        Submit
      </button>
    </div>

    <div v-else>
      <h3>You submitted successfully!</h3>
      <button class="btn btn-success" v-on:click="newStudent">
        Add another student
      </button>
      <button
        @click="goToStudent()"
        class="btn btn-primary"
        style="margin-top:12px"
      >
        List of students
      </button>
    </div>
  </div>
</template>

<script>
import http from "../http-common";
export default {
  name: "add-student",
  data() {
    return {
      cities: [],
      student: {
        studentId: 0,
        firstname: "",
        lastname: "",
        email: "",
        indexNumber: "",
        indexYear: "",
        address: "",
        currentYearOfStudy: "",
        city: 0,
      },

      submitted: false,
    };
  },
  created() {
    this.cityList();
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
    saveStudent() {
      var data = {
        studentId: this.student.studentId,
        firstname: this.student.firstname,
        lastname: this.student.lastname,
        email: this.student.email,
        indexNumber: this.student.indexNumber,
        indexYear: this.student.indexYear,
        address: this.student.address,
        city: this.student.city,
        currentYearOfStudy: this.student.currentYearOfStudy,
      };

      http
        .post("/students", data)
        .then((response) => {
          this.student.studentId = response.data.studentId;
          this.student.city = response.data.city;
          console.log(response.data.studentId);
        })
        .catch((e) => {
          console.log(e);
        });

      this.submitted = true;
    },
    goToStudent() {
      this.$router.push("/students");
    },
    newStudent() {
      this.submitted = false;
      this.student = {};
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
