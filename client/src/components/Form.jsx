import React, { useState } from 'react'

export const Form = ( { fields, onSubmit }) => {
    
    const [form, setForm] = useState([]);
    const handleChange = (e) => {
        const { name, value } = e.target;
        setForm({
          ...form,
          [name]: value,
        });
      };

      const handleSubmit = (e) => {
        e.preventDefault();
        onSubmit(form);
      }


  return (
    <form onSubmit={handleSubmit} className="space-y-6">
      {fields.map((field) => (
        <div key={field.name} className="form-group">
          <label className="block font-medium text-gray-700">
            {field.label}
          </label>
          <input
            type={field.type || 'text'}
            name={field.name}
            value={formState[field.name]}
            onChange={handleChange}
            placeholder={field.placeholder}
            className="mt-1 block w-full p-2 border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
          />
          {errors[field.name] && (
            <span className="text-sm text-red-600">{errors[field.name]}</span>
          )}
        </div>
      ))}
      <button
        type="submit"
        className="bg-indigo-600 text-white py-2 px-4 rounded-md hover:bg-indigo-700 transition duration-200"
      >
        Save
      </button>
    </form>
  )
}

