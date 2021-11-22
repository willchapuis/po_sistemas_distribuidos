const mongoose = require('mongoose');
const AlunoSchema = new mongoose.Schema({
    nome: String,
    curso: String,
    semestre: Number,
    ra: Number,
    cpf: Number,
    cidade: String,
});

module.exports = mongoose.model("Aluno", AlunoSchema);