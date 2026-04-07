from flask import Flask, request, jsonify, render_template, redirect, session
from flask_sqlalchemy import SQLAlchemy
from werkzeug.security import generate_password_hash, check_password_hash

app = Flask(__name__)

# 🔐 Secret key
app.config['SECRET_KEY'] = 'secret123'

# Database config
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///employees.db'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False

db = SQLAlchemy(app)

# ================= MODELS =================

class User(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    username = db.Column(db.String(100), unique=True)
    password = db.Column(db.String(200))


class Employee(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(100))
    email = db.Column(db.String(100))
    department = db.Column(db.String(100))
    role = db.Column(db.String(100))

    def to_dict(self):
        return {
            "id": self.id,
            "name": self.name,
            "email": self.email,
            "department": self.department,
            "role": self.role
        }


# ================= DB CREATE =================
with app.app_context():
    db.create_all()


# ================= AUTH =================

@app.route('/register', methods=['GET', 'POST'])
def register():
    if request.method == 'POST':
        username = request.form['username']
        password = generate_password_hash(request.form['password'])

        user = User(username=username, password=password)
        db.session.add(user)
        db.session.commit()

        return redirect('/login')

    return render_template("register.html")


@app.route('/login', methods=['GET', 'POST'])
def login():
    if request.method == 'POST':
        user = User.query.filter_by(username=request.form['username']).first()

        if user and check_password_hash(user.password, request.form['password']):
            session['user'] = user.username
            return redirect('/dashboard')

        return "Invalid Credentials"

    return render_template("login.html")


@app.route('/logout')
def logout():
    session.pop('user', None)
    return redirect('/login')


# ================= PAGES =================

@app.route("/")
def home():
    return redirect('/login')


@app.route("/dashboard")
def dashboard():
    if 'user' not in session:
        return redirect('/login')

    count = Employee.query.count()
    return render_template("dashboard.html", count=count)


@app.route("/employees_page")
def employees_page():
    if 'user' not in session:
        return redirect('/login')

    employees = Employee.query.all()
    return render_template("employees.html", employees=employees)


# ================= FORM CRUD =================

@app.route('/add_employee', methods=['POST'])
def add_employee_form():
    if 'user' not in session:
        return redirect('/login')

    data = request.form

    employee = Employee(
        name=data.get('name'),
        email=data.get('email'),
        department=data.get('department'),
        role=data.get('role')
    )

    db.session.add(employee)
    db.session.commit()

    return redirect('/employees_page')


@app.route('/update/<int:id>', methods=['POST'])
def update_employee_form(id):
    if 'user' not in session:
        return redirect('/login')

    employee = Employee.query.get(id)

    if employee:
        employee.name = request.form.get('name')
        employee.email = request.form.get('email')
        employee.department = request.form.get('department')
        employee.role = request.form.get('role')

        db.session.commit()

    return redirect('/employees_page')


@app.route('/delete/<int:id>')
def delete_employee_form(id):
    if 'user' not in session:
        return redirect('/login')

    employee = Employee.query.get(id)

    if employee:
        db.session.delete(employee)
        db.session.commit()

    return redirect('/employees_page')


# ================= API (UNCHANGED) =================

@app.route('/employees', methods=['POST'])
def add_employee():
    data = request.json

    employee = Employee(
        name=data.get('name'),
        email=data.get('email'),
        department=data.get('department'),
        role=data.get('role')
    )

    db.session.add(employee)
    db.session.commit()

    return jsonify({
        "message": "Employee added successfully",
        "employee": employee.to_dict()
    })


@app.route('/employees', methods=['GET'])
def get_employees():
    employees = Employee.query.all()
    return jsonify([emp.to_dict() for emp in employees])


@app.route('/employees/<int:id>', methods=['PUT'])
def update_employee(id):
    employee = Employee.query.get(id)

    if not employee:
        return jsonify({"error": "Employee not found"}), 404

    data = request.json

    employee.name = data.get('name', employee.name)
    employee.email = data.get('email', employee.email)
    employee.department = data.get('department', employee.department)
    employee.role = data.get('role', employee.role)

    db.session.commit()

    return jsonify({
        "message": "Employee updated successfully",
        "employee": employee.to_dict()
    })


@app.route('/employees/<int:id>', methods=['DELETE'])
def delete_employee(id):
    employee = Employee.query.get(id)

    if not employee:
        return jsonify({"error": "Employee not found"}), 404

    db.session.delete(employee)
    db.session.commit()

    return jsonify({"message": "Employee deleted successfully"})


# ================= RUN =================
if __name__ == '__main__':
    app.run(debug=True)