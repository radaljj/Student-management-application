<template>
  <div class="submitform">
    <div v-if="!submitted">
    
      <div class="form-group">
        <label for="esp">Exam date</label>
        <input 
          type="date"
          class="form-control"
          id="date"
          required
          v-model="exam.period"
          name="date"
        />
      </div>

      <div class="form-group">
        <label  style="margin-top:30px"   >Professors</label>
        <select v-model="exam.professor">
          <option v-for="professor in professors" v-bind:key="professor.professorId" :value="professor">
            {{ professor.firstname }} {{professor.lastname}}
          </option>
        </select>
      </div>

     
      <div class="form-group">
        <label style="margin:15px">Subjects</label>
       <select v-model="exam.subject">
          <option
            v-for="subject in subjects"
            v-bind:key="subject.idSubject "
            :value="subject"
          >
            {{ subject.name }} 
          </option>
        </select>
      </div>
      <button
        v-on:click="saveExam"
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
        @click="goToExams()"
        class="btn btn-primary"
        style="margin-top:12px"
      >
        List of exams
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
      professors: [],
      subjects: [],
      exam: {
        examId: 0,
        professor: "",
        subject: "",
        period: ""
        
      },

      submitted: false,
    };
  },
  created() {
    this.professorList();
    this.subjectList();
  },
  methods: {
    professorList() {
      http
        .get("professor/getProfessors")
        .then((response) => {
          this.professors = response.data;
        })
        .catch((err) => console.log("Error while displaying professors" + err));
    },
    subjectList() {
      http
        .get("subjects/getSubjects")
        .then((response) => {
          this.subjects = response.data;
        })
        .catch((err) => console.log("Error while displaying subjects" + err));
    },
    saveExam() {
      var data = {
        examId: this.exam.examId,
        professor: this.exam.professor,
       subject: this.exam.subject,
        period: this.exam.period,
        
      };

      http
        .post("/exam", data)
        .then((response) => {
          this.exam.examId = response.data.examId;
          this.exam.professor = response.data.professor;
          this.exam.subject = response.data.subject;
          console.log(response.data.examId);
        })
        .catch((e) => {
          console.log(e);
        });

      this.submitted = true;
    },
    goToExams() {
      this.$router.push("/exams");
    },
    newProfessor() {
      this.submitted = false;
      this.exam = {};
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
