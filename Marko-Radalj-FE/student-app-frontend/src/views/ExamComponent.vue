<template>
  <div class="container">
    <h3>All Exams</h3>
    <table class="table">
      <thead>
        <tr>
          <th>Professor first name</th>
          <th>Professor first name</th>
          <th>Subject name</th>
          <th>Exam period date</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="exam in exams" v-bind:key="exam.examId">
          <td>{{ exam.professor.firstname }}</td>
          <td>{{ exam.professor.lastname }}</td>
          <td>{{ exam.subject.name }}</td>
          <td>{{ exam.period}}</td>
          <td>
          </td>
        </tr>
      </tbody>
    </table>
    <div>
      <button
        @click="goToAddExam()"
        class="btn btn-primary "
        style="margin-right:1050px"
      >
        Add new exam
      </button>
    </div>
  </div>
</template>
<script>
import axios from "axios";
export default {
  name: "ProfessorComponent",
  data() {
    return {
      exams: [],
      editProfessor: null,
    };
  },
  created() {
    this.examList();
   
  },
  methods: {
    examList() {
      axios
        .get("http://localhost:8080/exam/getExams", {})
        .then((response) => {
          this.exams = response.data;
        })
        .catch((err) =>
          console.log("Error while displaying exams " + err)
        );
    },

    goToAddExam() {
      this.$router.push("/addExam");
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
