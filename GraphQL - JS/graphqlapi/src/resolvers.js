const Aluno = require('./Aluno');

module.exports = {
    Query: {
        alunos: () => Aluno.find(),
        aluno: (_, { id }) => Aluno.findById(id)
    },

    Mutation: {
        criarAluno: (_, { nome, curso, semestre, ra, cpf, cidade }) => Aluno.create({ nome, curso, semestre, ra, cpf, cidade }),
        removerAlunoById: (_, { id }) => Aluno.findByIdAndDelete(id),
        editarAlunoById: (_, { id, nome, curso, semestre, ra, cpf, cidade }) => Aluno.findByIdAndUpdate(id, { nome, curso, semestre, ra, cpf, cidade })
    },
};