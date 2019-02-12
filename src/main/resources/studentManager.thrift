namespace java com.student


enum Division {
A,B,C,D
}

struct Student {
1: i32 id,
2: optional string name,
3: optional i32 standard,
4: optional Division division
5: optional i32 score
}


service StudentService {
Student getStudent (1:i32 id),
void addStudent(1:Student student),
list<Student> getAll(),
void updateSudent(1: Student student),
void deleteStudent(1:i32 id)
}

