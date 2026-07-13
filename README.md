# TaskFlow API

## Objetivo
O TaskFlow API é um sistema de gerenciamento de tarefas desenvolvido para auxiliar equipes no acompanhamento de atividades, priorização de demandas e organização do fluxo de trabalho.

## Escopo Inicial
O sistema permitirá:
* Criar tarefas
* Listar tarefas
* Atualizar tarefas
* Excluir tarefas
* Definir prioridade
* Alterar status das tarefas

## Metodologia Utilizada
O projeto será desenvolvido utilizando a metodologia Kanban para organização das atividades e acompanhamento do progresso das tarefas. O desenvolvimento seguirá práticas de Engenharia de Software, incluindo planejamento, controle de mudanças, versionamento de código, testes automatizados e integração contínua.

## Tecnologias
* Java
* Spring Boot
* JPA/Hibernate
* MySQL
* JUnit
* GitHub Actions
* IntelliJ IDEA

## Estrutura Inicial da Entidade Principal
A entidade principal do sistema será a Task (Tarefa), contendo os seguintes atributos:
* id
* titulo
* descricao
* prioridade
* status
* dataCriacao

## Mudanças de Escopo
Para agregar valor ao produto e atender aos requisitos de evolução arquitetural, o escopo foi expandido com os seguintes incrementos:

1. **Introdução da Entidade Category (Categoria):** O grande diferencial implementado. Em vez de tarefas isoladas, agora o sistema suporta o agrupamento de atividades por categorias personalizadas (ex: Trabalho, Estudos, Pessoal), permitindo uma organização muito mais granular e relacional.
2. **Vinculação de Usuários (User):** Cada tarefa agora pertence obrigatoriamente a um usuário responsável, garantindo a auditoria e o controle de quem deve executar a atividade.
3. **Regras de Negócio e Restrições de Integridade:** Implementação de travas de segurança na camada de serviço (`Service`), incluindo a validação de campos obrigatórios (como títulos vazios) e restrição de e-mail único no banco de dados (`409 Conflict`) para evitar duplicidade de contas.

## Organização das Atividades
O gerenciamento das tarefas do projeto será realizado por meio do GitHub Projects, utilizando um quadro Kanban com as colunas:
* To Do
* In Progress
* In Review
* Done

O objetivo é acompanhar a evolução do projeto de forma visual e organizada, seguindo os princípios das metodologias ágeis.

## Futuras Evoluções e Backlog do Projeto

O TaskFlow API foi concebido com uma arquitetura flexível que permite a sua expansão em sprints subsequentes. Como propostas de melhorias futuras para evolução do produto, destacam-se:

1. **Implementação de Controle de Acesso Baseado em Perfis (RBAC):** Introdução de segurança a nível de rotas com Spring Security para segregar as permissões do sistema entre dois perfis distintos de usuários:
    * **Membro:** Perfil operacional com permissões para criar, listar e atualizar suas próprias tarefas, além de enviar solicitações de exclusão.
    * **Gestor:** Perfil administrativo com autonomia total sobre o fluxo, incluindo a aprovação de solicitações de deleção e gerenciamento de membros.
2. **Mecanismo de Auditoria e Soft Delete:** Evolução do método de exclusão direta para um fluxo de auditoria, onde tarefas deletadas passam por uma fila de aprovação antes da remoção física no banco de dados MySQL.