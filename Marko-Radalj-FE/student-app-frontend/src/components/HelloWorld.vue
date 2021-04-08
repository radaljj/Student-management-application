<template>
  <div class="hello">
      <div class="container">
        <!-- Example row of columns -->
        <div class="row">
          <div class="col-md-4">
            <h2>Student CRUD</h2>
            <p>CRUD operations on Student entity</p>
            <p><a class="btn btn-secondary" href="#" role="button" @click="goToStudent()">Student details &raquo;</a></p>
          </div>
          <div class="col-md-4">
            <h2>Professor CRUD</h2>
            <p>CRUD operations on Professor entity</p>
            <p><a class="btn btn-secondary" href="#" role="button " @click="goToProfessor()">Professor details &raquo;</a></p>
          </div>
          <div class="col-md-4">
            <h2>Subjects CRUD</h2>
            <p>CRUD operations on Subject entity</p>
            <p><a class="btn btn-secondary" href="#" role="button" @click="goToSubject()">Subject details &raquo;</a></p>
          </div>
        </div>

        <hr>

<div class="dugmici">
    <button
        @click="goToAddExam()"
        class="btn btn-primary"
      
      >
       Insert new exam
      </button>

     <button
        @click="goToExam()"
        class="btn btn-primary"
        style="margin-left:15px"
      >
       List of exams
      </button>

      <button
        @click="goToRegistratedExams()"
        class="btn btn-primary"
        style="margin-left:15px"
      >
       Registrated exams
      </button>
      </div>
      </div> <!-- /container -->
  </div>
  
</template>

<script>
export default {
  name: 'HelloWorld',
  props: {
    msg: String
  },
  methods:{
     goToSubject() {
      this.$router.push("/subjects");
    },
     goToStudent() {
      this.$router.push("/students");
    },
     goToProfessor() {
      this.$router.push("/professors");
    },goToAddExam() {
      this.$router.push("/addExam");
    }, 
    goToRegistratedExams() {
      this.$router.push("/registratedExams");
    },
    goToExam() {
      this.$router.push("/exams");
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
.dugmici{
  margin-top:60px;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
h3,h4{
  color:black;
}
</style>
